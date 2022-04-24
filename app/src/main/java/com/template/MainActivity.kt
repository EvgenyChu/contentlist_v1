package com.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.template.ui.BottomBar
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
                        "MenuScreen/change/{counter}" -> false
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
                    },
                    bottomBar = {
                        BottomBarHost(navController)
                    }
                ) {
                    NavHost(navController = navController, startDestination = "StartScreen") {
                        composable("StartScreen") { StartScreen(navController = navController) }
                        composable("MenuScreen") { MenuScreen(navController = navController, counter = 1) }
                        composable(
                            "MenuScreen/change/{counter}",
                            arguments = listOf(navArgument("counter") { type = NavType.IntType })
                        ) { MenuScreen(navController = navController, counter = it.arguments?.getInt("counter")) }
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
        "MenuScreen/change/{counter}" -> ToolBar(onMenuClick = onMenuClick)
    }
}

@Composable
private fun BottomBarHost(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    when (currentRoute) {
        "MenuScreen" -> BottomBar(navController = navController)
        "MenuScreen/change/{counter}" -> BottomBar(navController = navController)
    }
}