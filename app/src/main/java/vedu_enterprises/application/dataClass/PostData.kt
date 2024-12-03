package vedu_enterprises.application.dataClass

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class NavigationItem(
    val title: String,
    val backgroundColor: Color,
    @DrawableRes val iconResId: Int
)
