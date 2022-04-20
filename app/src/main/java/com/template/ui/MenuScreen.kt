package com.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun MenuScreen(navController: NavController) {
    Column {
        TextBox()
    }
}

@Composable
fun ToolBar(
    onMenuClick: () -> Unit
){
    TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
        IconButton(onClick = {onMenuClick()}) {
            Icon(
                Icons.Filled.Home,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Назад"
            )
        }
    }
}

@Composable
fun TextBox(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.onSecondary)
    ) {

    }
}