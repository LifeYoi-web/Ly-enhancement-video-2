package com.lifeyoi.cards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lifeyoi.cards.ui.AppNavHost
import com.lifeyoi.cards.ui.theme.LifeYoiCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LifeYoiCardsTheme {
                AppNavHost()
            }
        }
    }
}
