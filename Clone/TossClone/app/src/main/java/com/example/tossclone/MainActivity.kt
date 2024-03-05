package com.example.tossclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tossclone.constants.BENEFIT
import com.example.tossclone.constants.HOME
import com.example.tossclone.constants.MENU
import com.example.tossclone.constants.PAY
import com.example.tossclone.constants.STOCK
import com.example.tossclone.ui.screens.*
import com.example.tossclone.ui.theme.TossCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TossCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreenView()
                }
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationView(navController = navController) }
    ) {
        Box(Modifier.padding(it)){
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationView(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Benefit,
        BottomNavItem.Pay,
        BottomNavItem.Stock,
        BottomNavItem.Menu
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 12.sp) },
                selectedContentColor = Black,
                unselectedContentColor = Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home : BottomNavItem(R.string.toss_home, R.drawable.ic_home, HOME)
    object Benefit : BottomNavItem(R.string.toss_benefit, R.drawable.ic_loyalty, BENEFIT)
    object Pay : BottomNavItem(R.string.toss_pay, R.drawable.ic_bag, PAY)
    object Stock : BottomNavItem(R.string.toss_stock, R.drawable.ic_trending_up, STOCK)
    object Menu : BottomNavItem(R.string.toss_menu, R.drawable.ic_menu, MENU)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Benefit.screenRoute) {
            BenefitScreen()
        }
        composable(BottomNavItem.Pay.screenRoute) {
            PayScreen()
        }
        composable(BottomNavItem.Stock.screenRoute) {
            StockScreen()
        }
        composable(BottomNavItem.Menu.screenRoute) {
            MenuScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TossCloneTheme {
        HomeScreen()
    }
}