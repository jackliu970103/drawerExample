

package com.example.coculate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coculate.ui.theme.CoculateTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoculateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){

    var scope= rememberCoroutineScope()
    var scaffoldState= rememberScaffoldState()
    val navController= rememberNavController()
    Scaffold (
        scaffoldState=scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Compose Drawer Example") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                onDestinationClicked = { route ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(route) {

                    }
                }
            )
        }
    ){
            paddingValues ->
        NavHost(
            navController = navController,
            startDestination = DrawerScreens.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(DrawerScreens.Home.route) {
                HomeScreen()
            }
            composable(DrawerScreens.Account.route) {
                AccountScreen()
            }
            composable(DrawerScreens.Settings.route) {
                SettingsScreen()
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
fun AccountScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Account Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings Screen")
    }
}
@Composable
fun DrawerContent(
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Drawer Header", )
        Spacer(modifier = Modifier.height(16.dp))
        DrawerScreens.values().forEach { screen ->
            Spacer(Modifier.height(8.dp))
            Text(
                text = screen.title,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }
            )
        }
    }
}
@Composable
fun Navhosst(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { MainScreen() }
    }
}
data class draweritem(
    var name:String,
    var index :Int
)
enum class DrawerScreens(val title: String, val route: String) {
    Home("Home", "home"),
    Account("Account", "account"),
    Settings("Settings", "settings")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoculateTheme {

    }
}