package nl.kevinvandenhoek.pokedex.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.kevinvandenhoek.pokedex.utilities.onTap

data class Segment(
    val title: String,
    val content: @Composable () -> Unit
)

@Composable
fun SegmentedView(segments: List<Segment>, modifier: Modifier = Modifier) {
    var selected by remember { mutableStateOf(segments.first()) }

    Column {
        Row(
            modifier = modifier
        ) {
            segments.forEach { segment ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = segment.title,
                        textAlign = TextAlign.Center,
                        fontWeight = if (selected == segment) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier
                            .onTap { selected = segment }
                            .padding(vertical = 10.dp)
                    )
                    Box(
                        Modifier
                            .background(if (selected == segment) Color.Black else Color.LightGray)
                            .fillMaxWidth()
                            .height(3.dp)
                    )
                }
            }
        }
        selected.content()
    }
}

@Preview
@Composable
fun SegmentedViewPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEDF6FF)
    ) {
        Column {
            SegmentedView(
                segments = listOf(
                    Segment("About") {
                        Text("about section")
                    },
                    Segment("Stats") {
                        Text("stats section")
                    },
                    Segment("Evolution") {
                        Text("evolution section")
                    },
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}