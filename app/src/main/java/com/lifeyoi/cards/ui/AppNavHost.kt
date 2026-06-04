package com.lifeyoi.cards.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lifeyoi.cards.data.sampleCards
import com.lifeyoi.cards.ui.screens.DetailScreen
import com.lifeyoi.cards.ui.screens.HomeScreen
import com.lifeyoi.cards.ui.screens.MySubscriptionsScreen
import com.lifeyoi.cards.ui.screens.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val DETAIL = "detail"
    const val MY_SUBS = "mysubs"
    const val ARG_CARD_ID = "cardId"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    // Subscriptions the user has joined (kept in memory for the session).
    val owned = remember { mutableStateListOf<String>() }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
        ) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onFinish = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    },
                )
            }

            composable(Routes.HOME) {
                HomeScreen(
                    owned = owned,
                    onCardClick = { card ->
                        navController.navigate("${Routes.DETAIL}/${card.id}")
                    },
                    onOpenMySubs = { navController.navigate(Routes.MY_SUBS) },
                )
            }

            composable(
                route = "${Routes.DETAIL}/{${Routes.ARG_CARD_ID}}",
                arguments = listOf(navArgument(Routes.ARG_CARD_ID) { type = NavType.StringType }),
            ) { entry ->
                val cardId = entry.arguments?.getString(Routes.ARG_CARD_ID)
                val card = sampleCards.firstOrNull { it.id == cardId }
                if (card != null) {
                    DetailScreen(
                        card = card,
                        owned = owned.contains(card.id),
                        onToggleOwned = {
                            if (owned.contains(card.id)) owned.remove(card.id)
                            else owned.add(card.id)
                        },
                        onBack = { navController.popBackStack() },
                    )
                }
            }

            composable(Routes.MY_SUBS) {
                MySubscriptionsScreen(
                    owned = sampleCards.filter { owned.contains(it.id) },
                    onCardClick = { card ->
                        navController.navigate("${Routes.DETAIL}/${card.id}")
                    },
                    onBack = { navController.popBackStack() },
                    onBrowse = { navController.popBackStack() },
                )
            }
        }
    }
}
