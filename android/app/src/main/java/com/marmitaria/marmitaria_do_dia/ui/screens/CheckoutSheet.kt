package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.marmitaria.marmitaria_do_dia.ui.theme.BgGlass
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutSheet(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val details = uiState.orderDetails

    ModalBottomSheet(
        onDismissRequest = { viewModel.closeCheckout() },
        sheetState = sheetState,
        containerColor = BgPrimary,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Finalizar Pedido",
                    color = TextWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { viewModel.closeCheckout() }) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar", tint = TextMuted)
                }
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
                        fontSize = 14.sp
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
                        fontSize = 14.sp
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
                        label = { Text("CEP", fontSize = 12.sp, color = TextMuted) },
                        placeholder = { Text("01234-567", fontSize = 12.sp, color = TextMuted) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = outlinedColors()
                    )
                    OutlinedTextField(
                        value = details.neighborhood,
                        onValueChange = { b -> viewModel.updateOrderDetails { it.copy(neighborhood = b) } },
                        label = { Text("Bairro *", fontSize = 12.sp, color = TextMuted) },
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
                        label = { Text("Rua *", fontSize = 12.sp, color = TextMuted) },
                        modifier = Modifier.weight(2f),
                        shape = RoundedCornerShape(8.dp),
                        colors = outlinedColors()
                    )
                    OutlinedTextField(
                        value = details.number,
                        onValueChange = { num -> viewModel.updateOrderDetails { it.copy(number = num) } },
                        label = { Text("Nº *", fontSize = 12.sp, color = TextMuted) },
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
                        label = { Text("Complemento", fontSize = 12.sp, color = TextMuted) },
                        placeholder = { Text("Apto, Bloco", fontSize = 12.sp, color = TextMuted) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = outlinedColors()
                    )
                    OutlinedTextField(
                        value = details.reference,
                        onValueChange = { r -> viewModel.updateOrderDetails { it.copy(reference = r) } },
                        label = { Text("Ponto de Referência", fontSize = 12.sp, color = TextMuted) },
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
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🏪 Retirada no Balcão", color = PrimaryOrange, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(
                            "Endereço: ${MenuRepository.RESTAURANT_ADDRESS}\nDisponível a partir das ${MenuRepository.TAKEOUT_OPEN_TIME}.",
                            color = TextMuted,
                            fontSize = 12.sp,
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
                        .background(if (isSelected) BgSecondary else BgCard)
                        .border(1.dp, if (isSelected) PrimaryOrange else BorderLight, RoundedCornerShape(10.dp))
                        .clickable { viewModel.updateOrderDetails { it.copy(paymentMethod = method) } }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = { viewModel.updateOrderDetails { it.copy(paymentMethod = method) } },
                        colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(method.label, color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
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
                    Text("Precisa de troco?", color = TextWhite, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = !details.needChange,
                            onClick = { viewModel.updateOrderDetails { it.copy(needChange = false) } },
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                        )
                        Text("Não, valor exato", color = TextWhite, fontSize = 12.sp)

                        Spacer(modifier = Modifier.width(16.dp))

                        RadioButton(
                            selected = details.needChange,
                            onClick = { viewModel.updateOrderDetails { it.copy(needChange = true) } },
                            colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                        )
                        Text("Sim, preciso de troco", color = TextWhite, fontSize = 12.sp)
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
            Divider(color = BorderOrange, thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            // Totais
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal", color = TextMuted, fontSize = 14.sp)
                Text(WhatsAppHelper.formatPrice(uiState.subtotal), color = TextWhite, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Taxa de Entrega", color = TextMuted, fontSize = 14.sp)
                Text("Grátis", color = SuccessGreen, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total a Pagar", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    WhatsAppHelper.formatPrice(uiState.total),
                    color = TextGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.finalizeOrder() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
            ) {
                Text("Confirmar Pedido", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 15.sp)
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
