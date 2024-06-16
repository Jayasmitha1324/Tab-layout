package com.example.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContent {
             MyApp()
         }
    }
}

sealed class TabItem(val title: String, val icon: ImageVector) {
    object Home : TabItem("Home", Icons.Default.Home)
    object Call : TabItem("Call", Icons.Default.Call)
    object Share : TabItem("Share", Icons.Default.Share)
}

@Composable
fun MyApp() {
    val tabs = listOf(TabItem.Home, TabItem.Call ,TabItem.Share)
    val pagerState = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.value,
                edgePadding = 0.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        text = { Text(tab.title) },
                        selected = pagerState.value == index,
                        onClick = {
                            scope.launch {
                                pagerState.value = index
                            }
                        }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                when (pagerState.value) {
                    0 -> HomeScreen()
                    1 -> CallScreen()
                    2 -> ShareScreen()
                }
            }
        }
    }

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
    }
}

@Composable
fun CallScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Call Screen")
    }
}

@Composable
fun ShareScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Share Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
