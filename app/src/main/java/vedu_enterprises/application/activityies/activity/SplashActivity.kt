package vedu_enterprises.application.activityies.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.delay
import vedu_enterprises.application.R
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.ui.theme.VeduEnterprisesTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeduEnterprisesTheme {
                SplashScreen()
            }
        }
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)
        moveToNextPage(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC4EFB1)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ScaleText(text = context.getString(R.string.app_name))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    VeduEnterprisesTheme {
        SplashScreen()
    }
}

private fun moveToNextPage(context: Context) {
    val intent = if (Prefs.getBoolean(Constants.IS_LOGGED_IN, false)) {
        Intent(context, LoginActivity::class.java)
    } else {
        Intent(context, LoginActivity::class.java)
    }
    context.startActivity(intent)
    (context as? ComponentActivity)?.finish()
}


