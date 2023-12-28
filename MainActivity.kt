package nl.kevinvandenhoek.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.kevinvandenhoek.pokedex.ui.screens.HomeScreen.HomeScreen
import nl.kevinvandenhoek.pokedex.ui.screens.HomeScreen.ViewModel.HomeViewModel
import nl.kevinvandenhoek.pokedex.ui.screens.PokemonDetailScreen.PokemonDetailScreen
import nl.kevinvandenhoek.pokedex.ui.screens.PokemonDetailScreen.ViewModel.PokemonDetailViewModel
import nl.kevinvandenhoek.pokedex.ui.theme.JetpackComposeEdgeToEdgeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    JetpackComposeEdgeToEdgeSampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEDF6FF)
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        HomeViewModel(),
                        onSelect = { pokemon ->
                            navController.navigate("pokemon/${pokemon.id}")
                        }
                    )
                }
                composable(
                    "pokemon/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) {
                    val id = it.arguments?.getString("id")?.toInt() ?: 0
                    PokemonDetailScreen(PokemonDetailViewModel(id))
                }
            }
        }
    }
}