package vedu_enterprises.application.ui.theme

import android.content.Context
import androidx.compose.ui.graphics.Color
import android.widget.Toast
import kotlin.random.Random

object Utils {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getRandomLightColor(): Color {
        return Color(
            red = Random.nextFloat() * 0.5f + 0.5f,
            green = Random.nextFloat() * 0.5f + 0.5f,
            blue = Random.nextFloat() * 0.5f + 0.5f
        )
    }
}