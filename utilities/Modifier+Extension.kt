package nl.kevinvandenhoek.pokedex.utilities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

fun Modifier.onTap(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

fun Modifier.withShadow(
    x: Dp = 0.dp,
    y: Dp = 10.dp,
    alpha: Float = 0.1f,
    color: Color = Color.Black,
    radius: Dp = 10.dp,
    shape: Shape,
) = drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint()
        paint.asFrameworkPaint().apply {
            this.color = Color.Transparent.toArgb()
            setShadowLayer(
                radius.toPx(),
                x.toPx(),
                y.toPx(),
                ColorUtils.setAlphaComponent(
                    color.toArgb(),
                    (alpha * 255f).toInt()
                )
            )
        }
        val outline = shape.createOutline(size, layoutDirection, this)
        canvas.drawOutline(outline, paint)
    }
}