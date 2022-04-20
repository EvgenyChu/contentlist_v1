package com.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, bottom = 16.dp)
        ) {
            Icon(
                Icons.Filled.AccountBox,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Icon"
            )
            Text(
                text = "contentlist_v1",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Версия 1.1",
                style = MaterialTheme.typography.h4,
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Поделиться",
                    style = MaterialTheme.typography.h4
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Оценить",
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}