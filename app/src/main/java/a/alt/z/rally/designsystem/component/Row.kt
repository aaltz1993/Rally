package a.alt.z.rally.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

private val AmountDecimalFormat = DecimalFormat("#,###.##")

fun formatAmount(amount: Float): String = AmountDecimalFormat.format(amount)

private val AccountNumberDecimalFormat = DecimalFormat("####")

fun formatAccountNumber(number: Int): String = AccountNumberDecimalFormat.format(number)

@Composable
fun RallyRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: Float,
    negative: Boolean
) {
    Row(
        modifier = modifier
            .height(68.dp)
            .clearAndSetSemantics { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography

        Spacer(
            modifier
                .size(4.dp, 36.dp)
                .background(color = color)
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier) {
            Text(text = title, style = MaterialTheme.typography.body1)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = subtitle, style = MaterialTheme.typography.subtitle1)
            }
        }

        Spacer(Modifier.weight(1F))

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = if (negative) "-$ " else "$ ",
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = formatAmount(amount),
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(Modifier.width(16.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
            )
        }
    }

    Divider(color = MaterialTheme.colors.background, thickness = 1.dp)
}

/* ACCOUNT */
@Composable
fun AccountRow(
    modifier: Modifier = Modifier,
    name: String,
    number: String,
    balance: Float,
    color: Color
) {
    RallyRow(modifier, color, name, number, balance, false)
}

/* BILL */
@Composable
fun BillRow(
    modifier: Modifier = Modifier,
    color: Color,
    name: String,
    due: String,
    amount: Float
) {
    RallyRow(modifier, color, name, due, amount, true)
}