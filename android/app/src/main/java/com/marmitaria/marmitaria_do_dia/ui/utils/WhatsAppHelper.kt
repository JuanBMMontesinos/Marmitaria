package com.marmitaria.marmitaria_do_dia.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.marmitaria.marmitaria_do_dia.data.model.CartItem
import com.marmitaria.marmitaria_do_dia.data.model.DeliveryType
import com.marmitaria.marmitaria_do_dia.data.model.OrderDetails
import com.marmitaria.marmitaria_do_dia.data.model.PaymentMethod
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import java.net.URLEncoder
import java.text.NumberFormat
import java.util.Locale

object WhatsAppHelper {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    fun formatPrice(value: Double): String {
        return currencyFormat.format(value)
    }

    fun buildOrderMessage(orderDetails: OrderDetails, cartItems: List<CartItem>, total: Double): String {
        val itemsBuilder = StringBuilder()

        cartItems.forEach { item ->
            val detailsList = mutableListOf<String>()
            if (item.adicionais.isNotEmpty()) {
                val addsStr = item.adicionais.joinToString(", ") { "${it.name} (+ ${formatPrice(it.price)})" }
                detailsList.add("Adicionais: $addsStr")
            }
            if (item.preferences.isNotBlank()) {
                detailsList.add("Obs: \"${item.preferences}\"")
            }

            itemsBuilder.append("• *${item.qty}x ${item.name}* (${formatPrice(item.unitPrice)})\n")
            if (detailsList.isNotEmpty()) {
                itemsBuilder.append("   _${detailsList.joinToString(" | ")}_\n")
            }
        }

        val isDelivery = orderDetails.deliveryType == DeliveryType.DELIVERY
        val addressInfo = if (isDelivery) {
            val compStr = if (orderDetails.complement.isNotBlank()) "*Apto/Bloco:* ${orderDetails.complement}\n" else ""
            val refStr = if (orderDetails.reference.isNotBlank()) "\n*Referência:* ${orderDetails.reference}" else ""
            "*Rua:* ${orderDetails.street}, *Nº:* ${orderDetails.number}\n" +
                    compStr +
                    "*Bairro:* ${orderDetails.neighborhood}" +
                    refStr
        } else {
            "_Cliente fará a Retirada no Balcão (${MenuRepository.RESTAURANT_ADDRESS})_"
        }

        val changeInfo = if (orderDetails.paymentMethod == PaymentMethod.CASH_ON_DELIVERY) {
            if (orderDetails.needChange && orderDetails.changeFor > 0) {
                " (Troco para ${formatPrice(orderDetails.changeFor)})"
            } else {
                " (Sem troco)"
            }
        } else {
            ""
        }

        return """
*🍔 NOVO PEDIDO - MARMITARIA DO DIA*
----------------------------------
*CLIENTE:* ${orderDetails.clientName}
*TELEFONE:* ${orderDetails.clientPhone}

*ITENS DO PEDIDO:*
$itemsBuilder
*FORMA DE ENVIO:* ${if (isDelivery) "🚚 Delivery / Entrega" else "🏪 Retirada no Balcão"}
${if (isDelivery) "\n*ENDEREÇO DE ENTREGA:*\n$addressInfo" else "\n*PONTO DE RETIRADA:*\n$addressInfo"}

*FORMA DE PAGAMENTO:* ${orderDetails.paymentMethod.label}$changeInfo

*TOTAL DO PEDIDO:* *${formatPrice(total)}*
----------------------------------
Obrigado pela preferência! 😊
        """.trimIndent()
    }

    fun openWhatsApp(context: Context, message: String) {
        try {
            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=${MenuRepository.WHATSAPP_PHONE}&text=$encodedMessage")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.whatsapp")

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                // Fallback para qualquer aplicativo ou navegador
                val genericIntent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(genericIntent)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Não foi possível abrir o WhatsApp automaticamente.", Toast.LENGTH_LONG).show()
        }
    }
}
