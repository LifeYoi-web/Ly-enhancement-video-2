package com.lifeyoi.cards.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lifeyoi.cards.R
import com.lifeyoi.cards.data.Category
import com.lifeyoi.cards.data.SubscriptionCard
import com.lifeyoi.cards.data.featuredCards
import com.lifeyoi.cards.data.sampleCards
import com.lifeyoi.cards.ui.components.CategoryRow
import com.lifeyoi.cards.ui.components.FeaturedPager
import com.lifeyoi.cards.ui.components.SectionHeader
import com.lifeyoi.cards.ui.components.SubscriptionCardItem

@Composable
fun HomeScreen(
    owned: List<String>,
    onCardClick: (SubscriptionCard) -> Unit,
    onOpenMySubs: () -> Unit,
) {
    var selectedCategory by remember { mutableStateOf(Category.ALL) }
    val filtered = remember(selectedCategory) {
        if (selectedCategory == Category.ALL) sampleCards
        else sampleCards.filter { it.category == selectedCategory }
    }
    val rows = remember(filtered) { filtered.chunked(2) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                HomeTopBar(
                    ownedCount = owned.size,
                    onOpenMySubs = onOpenMySubs,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            item {
                SectionHeader(
                    title = "العروض المميزة",
                    subtitle = "أفضل الباقات المختارة لك",
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            item {
                FeaturedPager(
                    cards = featuredCards,
                    onCardClick = onCardClick,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            item {
                CategoryRow(
                    selected = selectedCategory,
                    onSelect = { selectedCategory = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                )
            }
            item {
                SectionHeader(
                    title = "كل البطاقات",
                    subtitle = "${filtered.size} باقة متاحة",
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            items(rows.size) { rowIndex ->
                val row = rows[rowIndex]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    GridCell(modifier = Modifier.weight(1f)) {
                        SubscriptionCardItem(
                            card = row[0],
                            owned = owned.contains(row[0].id),
                            onClick = { onCardClick(row[0]) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(0.84f),
                        )
                    }
                    GridCell(modifier = Modifier.weight(1f)) {
                        if (row.size > 1) {
                            SubscriptionCardItem(
                                card = row[1],
                                owned = owned.contains(row[1].id),
                                onClick = { onCardClick(row[1]) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.84f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.GridCell(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) { content() }
}

@Composable
private fun HomeTopBar(
    ownedCount: Int,
    onOpenMySubs: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ly_logo),
                contentDescription = "لايف يوي",
                tint = Color.Unspecified,
                modifier = Modifier.size(42.dp),
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = "أهلاً بك 👋",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = "بطاقات لايف يوي",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        MySubsButton(count = ownedCount, onClick = onOpenMySubs)
    }
}

@Composable
private fun MySubsButton(count: Int, onClick: () -> Unit) {
    Box {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Rounded.Bookmarks,
                contentDescription = "اشتراكاتي",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp),
            )
        }
        if (count > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
