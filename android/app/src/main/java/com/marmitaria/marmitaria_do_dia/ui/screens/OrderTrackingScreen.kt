package com.marmitaria.marmitaria_do_dia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.OutdoorGrill
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marmitaria.marmitaria_do_dia.ui.theme.BgCard
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.SuccessGreen
import com.marmitaria.marmitaria_do_dia.ui.theme.TextGold
import com.marmitaria.marmitaria_do_dia.ui.theme.TextMuted
import com.marmitaria.marmitaria_do_dia.ui.theme.TextWhite
import com.marmitaria.marmitaria_do_dia.ui.theme.WhatsAppGreen
import com.marmitaria.marmitaria_do_dia.ui.utils.WhatsAppHelper
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuUiState
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuViewModel

@Composable
fun OrderTrackingScreen(
    viewModel: MenuViewModel,
    uiState: MenuUiState
) {
    val context = LocalContext.current

    val stepTitles = listOf(
        "Recebido e Confirmado",
        "Na Cozinha (Preparando)",
        "Saiu para Entrega / Balcão",
        "Entregue (Bom Apetite!)"
    )

    val currentStatusHeader = when (uiState.trackingStep) {
        0 -> "Pedido Recebido pela Marmitaria!"
        1 -> "Seu marmitex está sendo preparado na cozinha!"
        2 -> "Saiu para Entrega / Pronto para Retirada!"
        else -> "Pedido entregue! Bom apetite!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card de Rastreamento
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = BgCard),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderOrange)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ícone animado
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(PrimaryOrange)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.OutdoorGrill,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = currentStatusHeader,
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Acompanhe abaixo o status em tempo real do seu pedido.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                )

                // Linha do tempo de 4 etapas
                stepTitles.forEachIndexed { index, title ->
                    val isDone = index < uiState.trackingStep
                    val isCurrent = index == uiState.trackingStep
                    val stepTime = uiState.trackingStepTimes.getOrElse(index) { "--:--" }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            isDone -> SuccessGreen
                                            isCurrent -> PrimaryOrange
                                            else -> BgCard
                                        }
                                    )
                                    .border(
                                        1.dp,
                                        if (isCurrent || isDone) androidx.compose.ui.graphics.Color.Transparent else TextMuted,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isDone) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = null,
                                        tint = TextWhite,
                                        modifier = Modifier.size(14.dp)
                                    )
                                } else {
                                    Text(
                                        text = "${index + 1}",
                                        color = if (isCurrent) androidx.compose.ui.graphics.Color.Black else TextMuted,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = title,
                                color = if (isCurrent) TextGold else if (isDone) TextWhite else TextMuted,
                                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        }

                        Text(
                            text = stepTime,
                            color = if (isCurrent) TextGold else TextMuted,
                            fontSize = 12.sp,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Alerta de envio pelo WhatsApp
        Text(
            text = "⚠️ IMPORTANTE: Envie o seu pedido pelo WhatsApp abaixo para oficializar a produção na nossa cozinha!",
            color = TextMuted,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Oficial WhatsApp
        Button(
            onClick = {
                val message = WhatsAppHelper.buildOrderMessage(
                    orderDetails = uiState.orderDetails,
                    cartItems = uiState.cartItems,
                    total = uiState.total,
                    orderDay = uiState.todayName
                )
                WhatsAppHelper.openWhatsApp(context, message)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
        ) {
            Icon(Icons.Default.Send, contentDescription = null, tint = TextWhite)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Enviar pelo WhatsApp",
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { viewModel.setNavTab(0) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMuted)
        ) {
            Text("Voltar para o Cardápio", fontSize = 13.sp)
        }
    }
}
