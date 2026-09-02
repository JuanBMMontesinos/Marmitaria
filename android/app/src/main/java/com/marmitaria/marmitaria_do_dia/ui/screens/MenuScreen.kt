package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.data.model.MealOption
import com.marmitaria.marmitaria_do_dia.data.model.MenuMode
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderLight
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.ErrorRed
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryDark
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.TextDark
import com.marmitaria.marmitaria_do_dia.ui.theme.TextGold
import com.marmitaria.marmitaria_do_dia.ui.theme.TextMuted
import com.marmitaria.marmitaria_do_dia.ui.theme.TextWhite
import com.marmitaria.marmitaria_do_dia.ui.utils.WhatsAppHelper
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuUiState
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Banner de Fechamento de Domingo
        if (uiState.showSundayWarning) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(ErrorRed)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "📢 ESTAMOS FECHADOS HOJE (DOMINGO)",
                            color = TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Consulte abaixo o menu semanal para planejar seus próximos pedidos!",
                            color = TextWhite,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Seletor Superior de Modo (Cardápio de Hoje vs Consulta Semanal)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgCard)
                    .border(1.dp, BorderOrange, RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                val isTodayMode = uiState.menuMode == MenuMode.TODAY_ONLY

                // Aba 1: Cardápio de Hoje
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isTodayMode) PrimaryOrange else Color.Transparent)
                        .clickable { viewModel.setMenuMode(MenuMode.TODAY_ONLY) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.RestaurantMenu,
                            contentDescription = null,
                            tint = if (isTodayMode) TextDark else TextWhite,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Cardápio de Hoje",
                            color = if (isTodayMode) TextDark else TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }

                // Aba 2: Consulta Semanal
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (!isTodayMode) PrimaryOrange else Color.Transparent)
                        .clickable { viewModel.setMenuMode(MenuMode.WEEKLY_CATALOG) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = if (!isTodayMode) TextDark else TextWhite,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Consulta Semanal",
                            color = if (!isTodayMode) TextDark else TextWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        // Modo Consulta Semanal: Seletor Horizontal de Dias e Banner Informativo
        if (uiState.menuMode == MenuMode.WEEKLY_CATALOG) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState)
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MenuRepository.weekdays.forEach { day ->
                        val isSelected = day == uiState.selectedDay
                        val isToday = day == uiState.todayName
                        val dayShort = day.substringBefore("-")

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isSelected) PrimaryOrange else BgCard)
                                .border(1.dp, if (isSelected) PrimaryOrange else BorderOrange, RoundedCornerShape(10.dp))
                                .clickable { viewModel.selectDay(day) }
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = dayShort,
                                    color = if (isSelected) TextDark else TextWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = if (isToday) "Hoje" else "Consulta",
                                    color = if (isSelected) TextDark.copy(alpha = 0.8f) else TextGold,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BgCard)
                        .border(1.dp, BorderOrange, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = TextGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Modo Consulta: Veja o cardápio da semana. Para fazer pedidos de hoje, acesse a aba 'Cardápio de Hoje'.",
                            color = TextMuted,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        // Título da Seção do Dia
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (uiState.menuMode == MenuMode.TODAY_ONLY) "Hoje (${uiState.todayName})" else uiState.selectedDay,
                        color = TextWhite,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (uiState.menuMode == MenuMode.TODAY_ONLY) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(PrimaryOrange)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "PEDIDOS ABERTOS",
                                color = TextDark,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(PrimaryDark.copy(alpha = 0.3f))
                        .border(1.dp, PrimaryOrange, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Acompanha Salada",
                        color = TextGold,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Grade de Pratos do Dia
        val dayMenu = uiState.currentDayMenu
        if (dayMenu != null) {
            items(dayMenu.options, key = { it.id }) { meal ->
                MealCard(
                    meal = meal,
                    isOrderingAllowed = uiState.isOrderingAllowedForSelectedDay,
                    dayName = uiState.displayedDay,
                    onOrderClick = { viewModel.openCustomizeModal(meal) }
                )
            }
        }

        // Seção de Bebidas
        item {
            Text(
                text = "Opções de Bebidas",
                color = TextWhite,
                fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )
        }

        items(MenuRepository.drinks, key = { it.id }) { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.addDrink(drink) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = drink.name,
                            color = TextWhite,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = drink.desc,
                            color = TextMuted,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                            fontSize = 12.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = WhatsAppHelper.formatPrice(drink.price),
                            color = TextGold,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(PrimaryOrange.copy(alpha = 0.12f))
                                .border(1.dp, PrimaryOrange, RoundedCornerShape(14.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Adicionar",
                                tint = PrimaryOrange,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun MealCard(
    meal: MealOption,
    isOrderingAllowed: Boolean,
    dayName: String,
    onOrderClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onOrderClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = BgCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, if (isOrderingAllowed) BorderOrange else BorderLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            // Faixa Lateral Esquerda
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(if (isOrderingAllowed) PrimaryOrange else TextMuted)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // Cabeçalho do Card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = meal.num.uppercase(),
                            color = if (isOrderingAllowed) PrimaryOrange else TextMuted,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = meal.name.uppercase(),
                            color = TextWhite,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 23.sp
                        )
                    }

                    Text(
                        text = WhatsAppHelper.formatPrice(meal.price),
                        color = TextGold,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Text(
                    text = "${meal.accompaniments}.",
                    color = TextMuted,
                    fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 6.dp, bottom = 4.dp)
                )

                // Divisória sutil
                HorizontalDivider(
                    color = BorderOrange.copy(alpha = 0.4f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                // Rodapé do Card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tag Acomp. Salada em Pílula
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(androidx.compose.ui.graphics.Color.White.copy(alpha = 0.05f))
                            .border(
                                1.dp,
                                androidx.compose.ui.graphics.Color.White.copy(alpha = 0.1f),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "ACOMP. SALADA",
                            color = TextMuted,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }

                    // Botão Pedir ou Indisponível
                    if (isOrderingAllowed) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryOrange.copy(alpha = 0.12f))
                                .border(1.dp, PrimaryOrange, RoundedCornerShape(8.dp))
                                .clickable { onOrderClick() }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = null,
                                    tint = PrimaryOrange,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "PEDIR HOJE",
                                    color = PrimaryOrange,
                                    fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    } else {
                        val dayShort = dayName.substringBefore("-")
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(BgPrimary)
                                .border(1.dp, BorderLight, RoundedCornerShape(8.dp))
                                .clickable { onOrderClick() }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "DISPONÍVEL NA $dayShort",
                                color = TextMuted,
                                fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

