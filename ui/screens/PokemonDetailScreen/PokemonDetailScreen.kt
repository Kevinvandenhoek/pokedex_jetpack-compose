package nl.kevinvandenhoek.pokedex.ui.screens.PokemonDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nl.kevinvandenhoek.pokedex.domain.models.Pokemon
import nl.kevinvandenhoek.pokedex.ui.composables.Segment
import nl.kevinvandenhoek.pokedex.ui.composables.SegmentedView
import nl.kevinvandenhoek.pokedex.ui.screens.PokemonDetailScreen.ViewModel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel) {
    val pokemon = viewModel.pokemon.collectAsState().value
    val details = viewModel.details.collectAsState().value

    Box(
        Modifier
            .statusBarsPadding()
    ) {
        Column(
            Modifier
                .fillMaxHeight()

        ) {
            Box(
                Modifier
                    .aspectRatio(1.3f)
                    .background(Color.Transparent)
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }
        Column {
            if (pokemon is Pokemon) {
                Column(
                    Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            pokemon.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Text(
                            "#${String.format("%03d", pokemon.id)}",
                            fontWeight = FontWeight.Light,
                            color = Color.LightGray,
                            fontSize = 32.sp
                        )
                    }
                    Row {
                        Text("Grass")
                    }
                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = null,
                        filterQuality = FilterQuality.None,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
                Box(
                    Modifier
                        .background(Color.White)
                        .padding(horizontal = 24.dp)
                        .fillMaxSize()
                ) {
                    SegmentedView(segments = listOf(
                        Segment("About") {
                            Box(Modifier.padding(vertical = 20.dp)) {
                                Text("about section")
                            }
                        },
                        Segment("Stats") {
                            Box(Modifier.padding(vertical = 20.dp)) {
                                Text("stats section")
                            }
                        },
                        Segment("Evolution") {
                            Box(Modifier.padding(vertical = 20.dp)) {
                                Text("evolution section")
                            }
                        },
                    ))
                }
            } else {
                Text("Loading...")
            }
        }
    }
}