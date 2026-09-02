package com.marmitaria.marmitaria_do_dia.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marmitaria.marmitaria_do_dia.ui.theme.BgGlass
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.BgSecondary
import com.marmitaria.marmitaria_do_dia.ui.theme.BorderOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryOrange
import com.marmitaria.marmitaria_do_dia.ui.theme.TextDark
import com.marmitaria.marmitaria_do_dia.ui.theme.TextGold
import com.marmitaria.marmitaria_do_dia.ui.theme.TextMuted
import com.marmitaria.marmitaria_do_dia.ui.theme.TextWhite
import com.marmitaria.marmitaria_do_dia.ui.viewmodel.MenuViewModel

@Composable
fun MainScreen(
    viewModel: MenuViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Exibição de Toasts reativos
    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearToast()
        }
    }

    // Animação de Pulso contínuo para o Badge de Delivery
    val infiniteTransition = androidx.compose.animation.core.rememberInfiniteTransition(label = "pulseTransition")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.06f,
        animationSpec = androidx.compose.animation.core.infiniteRepeatable(
            animation = androidx.compose.animation.core.tween(1000, easing = androidx.compose.animation.core.FastOutSlowInEasing),
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Scaffold(
        containerColor = BgPrimary,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                androidx.compose.ui.graphics.Color(0xE61C0F0A),
                                androidx.compose.ui.graphics.Color(0xD9120905)
                            )
                        )
                    )
                    .border(
                        androidx.compose.foundation.BorderStroke(
                            2.dp,
                            com.marmitaria.marmitaria_do_dia.ui.theme.PrimaryDark
                        )
                    )
                    .padding(top = 20.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título com sobreposição orgânica
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CARDÁPIO",
                        color = TextWhite,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.OswaldFamily,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = androidx.compose.ui.graphics.Shadow(
                                color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.85f),
                                offset = androidx.compose.ui.geometry.Offset(2f, 4f),
                                blurRadius = 8f
                            )
                        )
                    )
                    Text(
                        text = "do Dia",
                        color = TextGold,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.DancingScriptFamily,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .rotate(-6f),
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = androidx.compose.ui.graphics.Shadow(
                                color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.9f),
                                offset = androidx.compose.ui.geometry.Offset(1f, 2f),
                                blurRadius = 6f
                            )
                        )
                    )
                }

                // Badge de Delivery com Animação de Pulso contínua
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .graphicsLayer(scaleX = pulseScale, scaleY = pulseScale)
                        .clip(RoundedCornerShape(20.dp))
                        .background(PrimaryOrange.copy(alpha = 0.12f))
                        .border(1.dp, PrimaryOrange, RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "SOMENTE DELIVERY / ENTREGA",
                        color = PrimaryOrange,
                        fontFamily = com.marmitaria.marmitaria_do_dia.ui.theme.InterFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        fontSize = 11.sp
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = BgSecondary,
                tonalElevation = 8.dp
            ) {
                // Aba 1: Cardápio
                NavigationBarItem(
                    selected = uiState.activeNavTab == 0,
                    onClick = { viewModel.setNavTab(0) },
                    icon = {
                        Icon(Icons.Default.RestaurantMenu, contentDescription = "Cardápio")
                    },
                    label = { Text("Cardápio", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = navigationItemColors()
                )

                // Aba 2: Carrinho (Abre a gaveta lateral do carrinho)
                NavigationBarItem(
                    selected = uiState.isCartOpen,
                    onClick = { viewModel.openCart() },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (uiState.totalCartCount > 0) {
                                    Badge(
                                        containerColor = PrimaryOrange,
                                        contentColor = TextDark
                                    ) {
                                        Text("${uiState.totalCartCount}", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho")
                        }
                    },
                    label = { Text("Carrinho", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = navigationItemColors()
                )

                // Aba 3: Rastreador
                NavigationBarItem(
                    selected = uiState.activeNavTab == 2,
                    onClick = { viewModel.setNavTab(2) },
                    icon = {
                        Icon(Icons.Default.Assignment, contentDescription = "Rastreador")
                    },
                    label = { Text("Rastreador", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    colors = navigationItemColors()
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState.activeNavTab) {
                0 -> MenuScreen(viewModel = viewModel, uiState = uiState)
                2 -> OrderTrackingScreen(viewModel = viewModel, uiState = uiState)
                else -> MenuScreen(viewModel = viewModel, uiState = uiState)
            }

            // 1. BottomSheet Modal de Customização (Desliza de baixo para cima)
            if (uiState.isCustomizingOpen) {
                CustomizationSheet(viewModel = viewModel, uiState = uiState)
            }

            // 2. Gaveta Lateral do Carrinho (Desliza da direita para a esquerda)
            AnimatedVisibility(
                visible = uiState.isCartOpen,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.65f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            viewModel.closeCart()
                        }
                )
            }

            AnimatedVisibility(
                visible = uiState.isCartOpen,
                enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
                exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                CartSheet(viewModel = viewModel, uiState = uiState)
            }

            // 3. Gaveta Lateral do Checkout (Desliza da direita para a esquerda)
            AnimatedVisibility(
                visible = uiState.isCheckoutOpen,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.65f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            viewModel.closeCheckout()
                        }
                )
            }

            AnimatedVisibility(
                visible = uiState.isCheckoutOpen,
                enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
                exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                CheckoutSheet(viewModel = viewModel, uiState = uiState)
            }

            // 4. Modal do Pix
            if (uiState.isPixDialogOpen) {
                PixDialog(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun navigationItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = PrimaryOrange,
    selectedTextColor = PrimaryOrange,
    unselectedIconColor = TextMuted,
    unselectedTextColor = TextMuted,
    indicatorColor = BgPrimary
)
