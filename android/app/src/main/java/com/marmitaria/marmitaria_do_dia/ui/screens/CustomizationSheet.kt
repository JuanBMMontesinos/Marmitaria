package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.TextDark
import com.marmitaria.marmitaria_do_dia.ui.theme.TextGold
import com.marmitaria.marmitaria_do_dia.ui.theme.TextMuted
import com.marmitaria.marmitaria_do_dia.ui.theme.TextWhite
import com.marmitaria.marmitaria_do_dia.ui.utils.WhatsAppHelper
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuUiState
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationSheet(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val meal = uiState.customizingMeal ?: return
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { viewModel.closeCustomizeModal() },
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
            // Header do modal
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = meal.num.uppercase(),
                        color = PrimaryOrange,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = meal.name,
                        color = TextWhite,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )
                    Text(
                        text = WhatsAppHelper.formatPrice(uiState.customizingItemTotalPrice),
                        color = TextGold,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                IconButton(onClick = { viewModel.closeCustomizeModal() }) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar", tint = TextMuted)
                }
            }

            Text(
                text = "${meal.accompaniments}. Todas as marmitas acompanham salada.",
                color = TextMuted,
                fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            // Observações
            Text(
                text = "Observações do Pedido",
                color = TextGold,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = uiState.customizingPreferences,
                onValueChange = { viewModel.updateCustomizingPreferences(it) },
                placeholder = {
                    Text(
                        "Deseja retirar algo? (Ex: sem cebola, feijão separado)",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryOrange,
                    unfocusedBorderColor = BorderOrange,
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    focusedContainerColor = BgCard,
                    unfocusedContainerColor = BgCard
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Adicionais
            Text(
                text = "Adicionais Extras",
                color = TextGold,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            MenuRepository.availableAddons.forEach { addon ->
                val isChecked = uiState.customizingSelectedAddons.any { it.id == addon.id }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BgCard)
                        .border(1.dp, BorderOrange, RoundedCornerShape(8.dp))
                        .clickable { viewModel.toggleAddon(addon) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { viewModel.toggleAddon(addon) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryOrange,
                                checkmarkColor = TextDark
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = addon.name,
                            color = TextWhite,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = "+ ${WhatsAppHelper.formatPrice(addon.price)}",
                        color = TextGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quantidade e Botão Confirmar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Controle de quantidade
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(BgCard)
                        .border(1.dp, BorderOrange, RoundedCornerShape(8.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.setCustomizingQty(-1) }) {
                        Icon(Icons.Default.Remove, contentDescription = "Diminuir", tint = PrimaryOrange)
                    }
                    Text(
                        text = "${uiState.customizingQty}",
                        color = TextWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { viewModel.setCustomizingQty(1) }) {
                        Icon(Icons.Default.Add, contentDescription = "Aumentar", tint = PrimaryOrange)
                    }
                }

                Button(
                    onClick = { viewModel.confirmCustomize() },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                ) {
                    Text(
                        text = "Confirmar e Adicionar",
                        color = TextDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
