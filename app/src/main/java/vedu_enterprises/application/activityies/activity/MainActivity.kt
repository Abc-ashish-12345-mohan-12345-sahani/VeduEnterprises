package vedu_enterprises.application.activityies.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pixplicity.easyprefs.library.Prefs
import vedu_enterprises.application.R
import vedu_enterprises.application.dataClass.NavigationItem
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.VeduEnterprisesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeduEnterprisesTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val username = Prefs.getString(Constants.USERNAME)
    val imageUrl = remember { Prefs.getString(Constants.USER_IMAGE) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFA4DF91), Color.White
                    )
                )
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                IconButton(
                    onClick = {
                        context.startActivity(
                            Intent(
                                context, ProfileActivity::class.java
                            )
                        )
                    }, modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    if (imageUrl.isNullOrEmpty()) {
                        Icon(
                            imageVector = Icons.Default.PersonOutline,
                            contentDescription = "Profile",
                            tint = Color.Black
                        )
                    } else {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Black, CircleShape),
                            placeholder = painterResource(R.drawable.baseline_person_24),
                            error = painterResource(R.drawable.baseline_person_24)
                        )
                    }
                }
                Column(modifier = Modifier.padding(start = 5.dp)) {
                    Text(
                        text = stringResource(R.string.hello),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Text(
                        text = username, fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    shape = RoundedCornerShape(15.dp),
                    color = Color.White,
                    modifier = Modifier.size(45.dp),
                ) {
                    IconButton(
                        onClick = { (context as? ComponentActivity)?.finish() }
                    ) {
                        Icon(Icons.Default.Notifications, "notification")
                    }
                }
            }
        }
        Column {
            Text(
                text = stringResource(R.string.let_s_make),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.something_creative),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        MetroNavigationScreen(context)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VeduEnterprisesTheme {
        HomeScreen()
    }
}

@Composable
fun MetroNavigationScreen(context: Context) {
    val items = listOf(
        NavigationItem(
            "Coffee Mug", Color(0xFF64B5F6), R.drawable.coffee_mug
        ), NavigationItem(
            "Fare Between\nStations", Color(0xFFFFB74D), R.drawable.coffee_mug
        ), NavigationItem(
            "First/Last Train\nTimings", Color(0xFFF48FB1), R.drawable.coffee_mug
        ), NavigationItem(
            "Gates, Directions\nand Station Info", Color(0xFF81C784), R.drawable.coffee_mug
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            NavigationCard(context, item)
        }
    }

}

@Composable
fun NavigationCard(context: Context, item: NavigationItem) {
    Card(
        onClick = {
            context.startActivity(Intent(context, PreviewActivity::class.java).apply {
                putExtra(Constants.ITEM_NAME, item.title).putExtra(
                    Constants.ITEM_IMAGE,
                    item.iconResId
                )
            })
        },
        modifier = Modifier
            .height(280.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = item.backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = item.iconResId),
                contentDescription = "Image description",
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}