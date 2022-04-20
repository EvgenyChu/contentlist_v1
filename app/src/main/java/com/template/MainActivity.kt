package com.template

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.template.ui.DrawerScreen
import com.template.ui.MenuScreen
import com.template.ui.ToolBar
import com.template.ui.theme.Contentlist_v1Theme
import com.template.ui.theme.StartScreen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            var isLockDrawer by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = Unit) {
                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    isLockDrawer = when (destination.route) {
                        "MenuScreen" -> false
                        else -> true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            }
            Contentlist_v1Theme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerGesturesEnabled = !isLockDrawer,
                    drawerContent = { DrawerScreen(navController = navController) },
                    topBar = {
                        ToolBarHost(navController, onMenuClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        })
                    }
                ) {
                    NavHost(navController = navController, startDestination = "StartScreen") {
                        composable("StartScreen") { StartScreen(navController = navController) }
                        composable("MenuScreen") { MenuScreen(navController = navController) }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@OptIn(InternalCoroutinesApi::class)
@Composable
private fun ToolBarHost(navController: NavController, onMenuClick: () -> Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    when (currentRoute) {
        "MenuScreen" -> ToolBar(onMenuClick = onMenuClick)
    }
}