package vedu_enterprises.application.activityies.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vedu_enterprises.application.R
import vedu_enterprises.application.activityies.activity.ui.theme.VeduEnterprisesTheme
import vedu_enterprises.application.ui.theme.Gray10
import vedu_enterprises.application.ui.theme.Gray20
import vedu_enterprises.application.ui.theme.LightBlue80

class CheckoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeduEnterprisesTheme {
                CheckoutPage(activity = this)
            }
        }
    }
}

@Composable
fun CheckoutPage(activity: ComponentActivity) {
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { ShowHeader(heading = "Checkout", activity = activity) }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(110.dp),
                    colors = CardDefaults.cardColors(containerColor = Gray10),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = LightBlue80.copy(alpha = 0.8f),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(80.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.coffee_mug),
                                contentDescription = "Preview Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = context.getString(R.string.app_name),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "This is the subtext",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Gray10),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    BillDetails()
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Gray20),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Total Amount",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "₹100.00", style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ), color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = stringResource(R.string.proceed_to_pay), fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    VeduEnterprisesTheme {
        CheckoutPage(activity = object : ComponentActivity() {})
    }
}