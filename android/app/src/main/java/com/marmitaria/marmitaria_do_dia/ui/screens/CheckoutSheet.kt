package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.data.model.DeliveryType
import com.marmitaria.marmitaria_do_dia.data.model.PaymentMethod
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BgSecondary
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderLight
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.SuccessGreen
import com.marmitaria.marmitaria_do_dia.ui.theme.TextDark
import com.marmitaria.marmitaria_do_dia.ui.theme.TextGold
import com.marmitaria.marmitaria_do_dia.ui.theme.TextMuted
import com.marmitaria.marmitaria_do_dia.ui.theme.TextWhite
import com.marmitaria.marmitaria_do_dia.ui.utils.WhatsAppHelper
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuUiState
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuViewModel

@Composable
fun CheckoutSheet(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val details = uiState.orderDetails

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = 440.dp)
            .fillMaxWidth(0.92f),
        color = BgSecondary,
        shadowElevation = 16.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOrange)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 16.dp)
        ) {
            // Header da Gaveta Lateral
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BgPrimary)
                    .border(1.dp, BorderOrange)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Finalizar Pedido",
                    color = TextWhite,
                    fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                IconButton(onClick = { viewModel.closeCheckout() }) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar", tint = TextMuted)
                }
            }

            // Corpo Rolável do Formulário
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(14.dp))

                // Badge do dia do pedido
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(BgCard)
                        .border(1.dp, BorderOrange, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "📅 Pedido para Hoje: ${uiState.todayName}",
                        color = TextGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Alternador Entrega vs Retirada
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(BgCard)
                        .border(1.dp, BorderOrange, RoundedCornerShape(10.dp))
                        .padding(4.dp)
                ) {
                    val isDelivery = details.deliveryType == DeliveryType.DELIVERY
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isDelivery) PrimaryOrange else BgCard)
                            .clickable { viewModel.updateOrderDetails { it.copy(deliveryType = DeliveryType.DELIVERY) } }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Entrega",
                            color = if (isDelivery) TextDark else TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (!isDelivery) PrimaryOrange else BgCard)
                            .clickable { viewModel.updateOrderDetails { it.copy(deliveryType = DeliveryType.TAKEOUT) } }
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Retirada no Balcão",
                            color = if (!isDelivery) TextDark else TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Seção: Seus Dados
                Text("Seus Dados", color = TextGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = details.clientName,
                    onValueChange = { name -> viewModel.updateOrderDetails { it.copy(clientName = name) } },
                    label = { Text("Seu Nome *", fontSize = 12.sp, color = TextMuted) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = outlinedColors()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = details.clientPhone,
                    onValueChange = { phone -> viewModel.updateOrderDetails { it.copy(clientPhone = phone) } },
                    label = { Text("WhatsApp para Contato *", fontSize = 12.sp, color = TextMuted) },
                    placeholder = { Text("Ex: (11) 99999-9999", fontSize = 12.sp, color = TextMuted) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = outlinedColors()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Seção: Endereço ou Ponto de Retirada
                if (details.deliveryType == DeliveryType.DELIVERY) {
                    Text("Endereço de Entrega", color = TextGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = details.cep,
                            onValueChange = { cep -> viewModel.updateOrderDetails { it.copy(cep = cep) } },
                            label = { Text("CEP", fontSize = 11.sp, color = TextMuted) },
                            placeholder = { Text("01234-567", fontSize = 11.sp, color = TextMuted) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                        OutlinedTextField(
                            value = details.neighborhood,
                            onValueChange = { b -> viewModel.updateOrderDetails { it.copy(neighborhood = b) } },
                            label = { Text("Bairro *", fontSize = 11.sp, color = TextMuted) },
                            modifier = Modifier.weight(1.5f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = details.street,
                            onValueChange = { s -> viewModel.updateOrderDetails { it.copy(street = s) } },
                            label = { Text("Rua *", fontSize = 11.sp, color = TextMuted) },
                            modifier = Modifier.weight(2f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                        OutlinedTextField(
                            value = details.number,
                            onValueChange = { num -> viewModel.updateOrderDetails { it.copy(number = num) } },
                            label = { Text("Nº *", fontSize = 11.sp, color = TextMuted) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = details.complement,
                            onValueChange = { c -> viewModel.updateOrderDetails { it.copy(complement = c) } },
                            label = { Text("Comp.", fontSize = 11.sp, color = TextMuted) },
                            placeholder = { Text("Apto", fontSize = 11.sp, color = TextMuted) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                        OutlinedTextField(
                            value = details.reference,
                            onValueChange = { r -> viewModel.updateOrderDetails { it.copy(reference = r) } },
                            label = { Text("Referência", fontSize = 11.sp, color = TextMuted) },
                            modifier = Modifier.weight(1.5f),
                            shape = RoundedCornerShape(8.dp),
                            colors = outlinedColors()
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(BgCard)
                            .border(1.dp, BorderOrange, RoundedCornerShape(10.dp))
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("🏪 Retirada no Balcão", color = PrimaryOrange, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(
                                "Endereço: ${MenuRepository.RESTAURANT_ADDRESS}\nDisponível a partir das ${MenuRepository.TAKEOUT_OPEN_TIME}.",
                                color = TextMuted,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Seção: Formas de Pagamento
                Text("Forma de Pagamento", color = TextGold, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                PaymentMethod.values().forEach { method ->
                    val isSelected = details.paymentMethod == method
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) BgPrimary else BgCard)
                            .border(1.dp, if (isSelected) PrimaryOrange else BorderLight, RoundedCornerShape(10.dp))
                            .clickable { viewModel.updateOrderDetails { it.copy(paymentMethod = method) } }
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { viewModel.updateOrderDetails { it.copy(paymentMethod = method) } },
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(method.label, color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(method.description, color = TextMuted, fontSize = 11.sp)
                        }
                    }
                }

                // Opção extra de troco se for dinheiro
                if (details.paymentMethod == PaymentMethod.CASH_ON_DELIVERY) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(BgCard)
                            .padding(12.dp)
                    ) {
                        Text("Precisa de troco?", color = TextWhite, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = !details.needChange,
                                onClick = { viewModel.updateOrderDetails { it.copy(needChange = false) } },
                                colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                            )
                            Text("Não, valor exato", color = TextWhite, fontSize = 11.sp)

                            Spacer(modifier = Modifier.width(12.dp))

                            RadioButton(
                                selected = details.needChange,
                                onClick = { viewModel.updateOrderDetails { it.copy(needChange = true) } },
                                colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                            )
                            Text("Sim, preciso de troco", color = TextWhite, fontSize = 11.sp)
                        }

                        if (details.needChange) {
                            OutlinedTextField(
                                value = if (details.changeFor > 0) details.changeFor.toString() else "",
                                onValueChange = { valStr ->
                                    val num = valStr.toDoubleOrNull() ?: 0.0
                                    viewModel.updateOrderDetails { it.copy(changeFor = num) }
                                },
                                label = { Text("Troco para quanto? (Ex: 50.00)", fontSize = 11.sp, color = TextMuted) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                colors = outlinedColors()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Rodapé Fixo da Gaveta Lateral
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BgPrimary)
                    .border(1.dp, BorderOrange)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal", color = TextMuted, fontSize = 13.sp)
                    Text(WhatsAppHelper.formatPrice(uiState.subtotal), color = TextWhite, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Taxa de Entrega", color = TextMuted, fontSize = 13.sp)
                    Text("Grátis", color = SuccessGreen, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total a Pagar", color = TextWhite, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(
                        WhatsAppHelper.formatPrice(uiState.total),
                        color = TextGold,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { viewModel.finalizeOrder() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                ) {
                    Text("Confirmar Pedido", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
private fun outlinedColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = PrimaryOrange,
    unfocusedBorderColor = BorderOrange,
    focusedTextColor = TextWhite,
    unfocusedTextColor = TextWhite,
    focusedContainerColor = BgCard,
    unfocusedContainerColor = BgCard
)
