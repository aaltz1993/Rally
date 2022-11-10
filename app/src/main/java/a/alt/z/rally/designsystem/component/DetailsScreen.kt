package a.alt.z.rally.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> RallyDetailsScreen(
    modifier: Modifier = Modifier,
    items: List<T>,
    colorOf: (T) -> Color,
    amountOf: (T) -> Float,
    totalAmount: Float,
    circleLabel: String,
    row: @Composable (T) -> Unit
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Box(Modifier.padding(16.dp)) {
            RallyAnimatedCircle(
                proportions = items.extractProportions(amountOf),
                colors = items.map(colorOf),
                modifier = Modifier
                    .height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            Column(Modifier.align(Alignment.Center)) {
                Text(
                    text = circleLabel,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = formatAmount(totalAmount),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Card {
            Column(Modifier.padding(12.dp)) {
                items.forEach { item ->
                    row(item)
                }
            }
        }
    }
}

fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = sumOf { selector(it).toDouble() }
    return map { (selector(it) / total).toFloat() }
}