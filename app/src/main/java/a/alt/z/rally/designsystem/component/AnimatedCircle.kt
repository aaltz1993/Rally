package a.alt.z.rally.designsystem.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private const val DividerLengthInDegrees = 1.8f

@Composable
fun RallyAnimatedCircle(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }

    val transition = updateTransition(currentState, null)

    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        },
        label = "FloatAnimation"
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0F
        } else {
            360F
        }
    }

    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = CubicBezierEasing(0F, 0.75F, 0.35F, 0.85F)
            )
        },
        label = "FloatAnimation"
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    val stroke = with(LocalDensity.current) { Stroke(5.dp.toPx()) }

    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2

        val topLeft = Offset(
            (size / 2.0F).width - innerRadius,
            (size / 2.0F).height - innerRadius
        )

        val size = Size(innerRadius * 2, innerRadius * 2)

        var startAngle = shift - 90f

        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = colors[index],
                startAngle = startAngle + DividerLengthInDegrees / 2,
                sweepAngle = sweep - DividerLengthInDegrees,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            startAngle += sweep
        }
    }
}

private enum class AnimatedCircleProgress { START, END }