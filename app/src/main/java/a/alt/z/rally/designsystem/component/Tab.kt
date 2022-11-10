package a.alt.z.rally.designsystem.component

import a.alt.z.rally.RallyDestination
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

private val TabHeight = 56.dp

private const val InactiveTabOpacity = 0.6F

private const val TabFadeInAnimationDuration = 150
private const val TabFadeOutAnimationDuration = 100
private const val TabFadeInAnimationDelay = 100

@Composable
fun RallyTabRow(
    screens: List<RallyDestination>,
    onTabSelected: (RallyDestination) -> Unit,
    currentScreen: RallyDestination
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(Modifier.selectableGroup()) {
            screens.forEach { screen ->
                RallyTab(
                    icon = screen.icon,
                    text = screen.route,
                    onTabSelected = { onTabSelected(screen) },
                    isSelected = screen == currentScreen
                )
            }
        }
    }
}

@Composable
private fun RallyTab(
    icon: ImageVector,
    text: String,
    onTabSelected: () -> Unit,
    isSelected: Boolean
) {
    val color = MaterialTheme.colors.onSurface

    val animationSpec = remember {
        tween<Color>(
            durationMillis = if (isSelected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }

    val tabTintColor by animateColorAsState(
        targetValue = if (isSelected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animationSpec
    )

    Row(
        Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = isSelected,
                onClick = onTabSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = tabTintColor)

        if (isSelected) {
            Spacer(Modifier.width(12.dp))

            Text(text = text.uppercase(Locale.getDefault()), color = tabTintColor)
        }
    }
}