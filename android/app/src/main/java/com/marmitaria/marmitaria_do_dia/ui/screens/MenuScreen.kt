package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.data.model.MealOption
import com.marmitaria.marmitaria_do_dia.data.repository.MenuRepository
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BgSecondary
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
import java.util.Calendar

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val scrollState = rememberScrollState()
    val todayIndex = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val todayName = when (todayIndex) {
        Calendar.MONDAY -> "Segunda-feira"
        Calendar.TUESDAY -> "Terça-feira"
        Calendar.WEDNESDAY -> "Quarta-feira"
        Calendar.THURSDAY -> "Quinta-feira"
        Calendar.FRIDAY -> "Sexta-feira"
        Calendar.SATURDAY -> "Sábado"
        else -> ""
    }

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
                        .padding(bottom = 12.dp)
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
                            text = "Consulte abaixo o menu de Segunda-feira e agende seu pedido!",
                            color = TextWhite,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // Seletor Horizontal de Dias da Semana
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MenuRepository.weekdays.forEach { day ->
                    val isSelected = day == uiState.selectedDay
                    val isToday = day == todayName
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
                                text = if (isToday) "Hoje" else "Cardápio",
                                color = if (isSelected) TextDark.copy(alpha = 0.8f) else TextGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Título da Seção do Dia
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiState.selectedDay,
                    color = TextWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

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
                shape = RoundedCornerShape(10.dp),
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = WhatsAppHelper.formatPrice(drink.price),
                            color = TextGold,
                            fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(PrimaryOrange),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Adicionar",
                                tint = TextDark,
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
    onOrderClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onOrderClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BgCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
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
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = WhatsAppHelper.formatPrice(meal.price),
                    color = TextGold,
                    fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "${meal.accompaniments}.",
                color = TextMuted,
                fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(BgPrimary)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Acomp. Salada",
                        color = TextMuted,
                        fontSize = 10.sp
                    )
                }

                Button(
                    onClick = onOrderClick,
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        tint = TextDark,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Pedir",
                        color = TextDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
