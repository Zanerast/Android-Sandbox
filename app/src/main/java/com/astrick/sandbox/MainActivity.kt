package com.astrick.sandbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScrollableWithPager()
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableWithPager() {
    val coroutine = rememberCoroutineScope()
    var currentTitleSize by remember { mutableStateOf(0.dp) }
    var textScale by remember { mutableFloatStateOf(1f) }
    var maxTitleSize by remember { mutableStateOf(0.dp) }
    
    val density = LocalDensity.current
    
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y.toInt()
                val newTitleSize = currentTitleSize + delta.dp
                val previousTitleSize = currentTitleSize
                
                currentTitleSize = newTitleSize.coerceIn(0.dp, maxTitleSize)
                val consumed = currentTitleSize - previousTitleSize
                
                textScale = if (maxTitleSize > 0.dp) currentTitleSize / maxTitleSize else 1f
                
                return Offset(0f, consumed.value)
            }
        }
    }
    Box(Modifier.nestedScroll(nestedScrollConnection)) {
        Text(
            text = "Big Title",
            style = MaterialTheme.typography.displayLarge,
            onTextLayout = { textLayoutResult ->
                val height = textLayoutResult.size.height
                if (maxTitleSize == 0.dp) {
                    maxTitleSize = height.dp
                    currentTitleSize = height.dp
                }
            },
            modifier = Modifier.wrapContentHeight()
        )
        Column(
            modifier = Modifier.offset {
                IntOffset(0, currentTitleSize.value.toInt())
            }
        ) {
            val pagerState = rememberPagerState { 2 }
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                tabs = {
                    Tab(
                        onClick = {
                            coroutine.launch {
                                pagerState.scrollToPosition(0)
                            }
                        },
                        content = {
                            Text("Tab 1")
                            
                        },
                        selected = pagerState.currentPage == 0,
                    )
                    Tab(
                        onClick = {
                            coroutine.launch {
                                pagerState.scrollToPosition(1)
                            }
                        },
                        content = {
                            Text("Tab 2")
                        },
                        selected = pagerState.currentPage == 1,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                divider = {
                
                },
                indicator = { tabPositions ->
                    if (pagerState.currentPage < tabPositions.size) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            color = Color.White.copy(alpha = 0.2f),
                        )
                    }
                }
            )
            
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> {
                        LazyColumn {
                            items(100) { index ->
                                Text(
                                    text = "Page 1 Item $index",
                                    color = Color.White
                                )
                            }
                        }
                    }
                    
                    1 -> {
                        LazyColumn {
                            items(100) { index ->
                                Text(
                                    text = "Page 2 Item $index",
                                    color = Color.White
                                )
                            }
                        }
                        
                    }
                }
            }
        }
        
        
    }
}

@OptIn(ExperimentalFoundationApi::class)
private suspend fun PagerState.scrollToPosition(page: Int) {
    animateScrollToPage(
        page = page,
        animationSpec = tween(durationMillis = 450)
    )
}


