package com.template.ui

import android.content.Intent
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.template.MenuViewModel
import com.template.R
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


@Composable
fun MenuScreen(navController: NavController, counter: Int?) {
    Column {
        TextBox(counter = counter)
    }
}

@Composable
fun ToolBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
        IconButton(onClick = { onMenuClick() }) {
            Icon(
                Icons.Filled.Home,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Назад"
            )
        }
    }
}

@Composable
fun TextBox(
    counter: Int?
) {
    val context = LocalContext.current
    var htmlPage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        try {
            val idFile: Int =
                context.resources.getIdentifier("page_${counter}", "raw", context.packageName)
            val fileName = context.resources.openRawResource(idFile)
            htmlPage = fileName.use {
                BufferedReader(it.reader()).readText()
            }
        } catch (e: Exception) {
            htmlPage = "Страница не найдена"
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.onSecondary)
            .verticalScroll(rememberScrollState())
    ) {

        Log.e("MenuScreen", "$htmlPage")
        if (htmlPage.isNotEmpty()) {
            AndroidView(factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    loadDataWithBaseURL(null, htmlPage, "text/HTML", "UTF-8", null)
                }
            })
        }
    }
}

@Composable
fun BottomBar(vm: MenuViewModel = viewModel(), navController: NavController) {
    val state by vm.state.collectAsState()
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(56.dp)
    ) {
        Button(
            onClick = {
                if (state.counter != 1) {
                    vm.updateCounterMinus()
                    navController.navigate("MenuScreen/change/${state.counter - 1}")
                }
                Log.e("MenuScreen", "${state.counter}")

            },
            modifier = Modifier
                .height(44.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "Назад",
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                vm.updateCounterPlus()
                navController.navigate("MenuScreen/change/${state.counter + 1}")
                Log.e("MenuScreen", "${state.counter}")
            },
            modifier = Modifier
                .height(44.dp)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "Вперед",
                style = MaterialTheme.typography.h4
            )
        }
    }
}


