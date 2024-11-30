package vedu_enterprises.application.activityies.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.delay
import vedu_enterprises.application.R
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.Utils
import vedu_enterprises.application.ui.theme.ui.theme.VeduEnterprisesTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeduEnterprisesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    SplashScreen(innerPadding, context)
                }
            }
        }
    }
}

@Composable
fun SplashScreen(innerPadding: PaddingValues, context: Context) {

    val isTextVisible = remember { mutableStateOf(false) }
    val backgroundColor = remember { mutableStateOf(Color.White) }

    LaunchedEffect(Unit) {
        backgroundColor.value = Utils.getRandomLightColor()
        delay(1000)
        isTextVisible.value = true
        delay(2000)
        moveToNextPage(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.value),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = "App Icon",
                tint = Color.White,
                modifier = Modifier.size(100.dp)
            )

            AnimatedVisibility(
                visible = isTextVisible.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000))
            ) {
                Text(
                    text = context.getString(R.string.app_name),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 100.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    VeduEnterprisesTheme {
        SplashScreen(innerPadding = PaddingValues(0.dp), context = LocalContext.current)
    }
}

private fun moveToNextPage(context: Context) {
    val intent = if (Prefs.getBoolean(Constants.IS_LOGGED_IN, false)) {
        Intent(context, MainActivity::class.java)
    } else {
        Intent(context, LoginActivity::class.java)
    }
    context.startActivity(intent)
    (context as? ComponentActivity)?.finish()
}


