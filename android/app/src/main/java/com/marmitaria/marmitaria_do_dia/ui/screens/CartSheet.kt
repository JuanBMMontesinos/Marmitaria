package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderLight
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.ErrorRed
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
fun CartSheet(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { viewModel.closeCart() },
        sheetState = sheetState,
        containerColor = BgPrimary,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
        ) {
            // Cabeçalho da Gaveta do Carrinho
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = PrimaryOrange,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Seu Carrinho",
                        color = TextWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(onClick = { viewModel.closeCart() }) {
                    Icon(Icons.Default.Close, contentDescription = "Fechar", tint = TextMuted)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (uiState.cartItems.isEmpty()) {
                // Estado Vazio
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = TextMuted,
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Seu carrinho está vazio!",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Volte ao cardápio e adicione marmitas fresquinhas para o seu almoço.",
                        color = TextMuted,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 6.dp, start = 24.dp, end = 24.dp)
                    )
                }
            } else {
                // Lista de Itens do Carrinho
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                ) {
                    items(uiState.cartItems, key = { it.cartId }) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(BgCard)
                                .border(1.dp, BorderLight, RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${item.qty}x ${item.name}",
                                        color = TextWhite,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    if (item.adicionais.isNotEmpty()) {
                                        Column(modifier = Modifier.padding(vertical = 2.dp)) {
                                            item.adicionais.forEach { add ->
                                                Text(
                                                    text = "+ ${add.name} (+ ${WhatsAppHelper.formatPrice(add.price)})",
                                                    color = TextGold,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            }
                                        }
                                    }

                                    if (item.preferences.isNotBlank()) {
                                        Text(
                                            text = "Obs: \"${item.preferences}\"",
                                            color = TextMuted,
                                            fontSize = 11.sp,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                        )
                                    }

                                    Text(
                                        text = WhatsAppHelper.formatPrice(item.totalPrice),
                                        color = TextGold,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    IconButton(
                                        onClick = { viewModel.removeCartItem(item.cartId) },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Remover",
                                            tint = ErrorRed,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Row(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(BgPrimary)
                                            .border(1.dp, BorderOrange, RoundedCornerShape(6.dp)),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            onClick = { viewModel.adjustCartQty(item.cartId, -1) },
                                            modifier = Modifier.size(26.dp)
                                        ) {
                                            Icon(Icons.Default.Remove, contentDescription = "Menos", tint = PrimaryOrange, modifier = Modifier.size(14.dp))
                                        }
                                        Text(
                                            text = "${item.qty}",
                                            color = TextWhite,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            modifier = Modifier.padding(horizontal = 6.dp)
                                        )
                                        IconButton(
                                            onClick = { viewModel.adjustCartQty(item.cartId, 1) },
                                            modifier = Modifier.size(26.dp)
                                        ) {
                                            Icon(Icons.Default.Add, contentDescription = "Mais", tint = PrimaryOrange, modifier = Modifier.size(14.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = BorderOrange, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))

                // Resumo de Totais
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
                    Text("Total do Pedido", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(
                        WhatsAppHelper.formatPrice(uiState.total),
                        color = TextGold,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ações do Carrinho
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.clearCart() },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed),
                        border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRed)
                    ) {
                        Text("Limpar", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { viewModel.openCheckout() },
                        modifier = Modifier
                            .weight(2f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                    ) {
                        Text("Avançar", color = TextDark, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
        }
    }
}
