package com.marmitaria.marmitaria_do_dia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.marmitaria.marmitaria_do_dia.ui.screens.MainScreen
import com.marmitaria.marmitaria_do_dia.ui.theme.BgPrimary
import com.marmitaria.marmitaria_do_dia.ui.theme.MarmitariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarmitariaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BgPrimary
                ) {
                    MainScreen()
                }
            }
        }
    }
}
