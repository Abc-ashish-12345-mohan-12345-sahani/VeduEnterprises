package vedu_enterprises.application.activityies.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BillDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Section Title
        Text(
            text = "Bill details",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Items total
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.List, // Replace with custom icon if needed
                    contentDescription = "Items"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Items total")
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFDCEEFF), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Saved ₹257",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF004AAD)
                    )
                }
            }
            Text(
                text = "₹1,504 ₹1,247",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Delivery charge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DirectionsBike, // Replace with delivery icon
                    contentDescription = "Delivery"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Delivery charge")
            }
            Text(
                text = "₹25 FREE",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF004AAD)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Handling charge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag, // Replace with bag icon
                    contentDescription = "Handling"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Handling charge")
            }
            Text(
                text = "₹2",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Divider(thickness = 1.dp)

        // Grand total
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Grand total",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "₹1,249",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(thickness = 1.dp)

        // Savings
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDCEEFF), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Your total savings",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF004AAD)
                )
                Text(
                    text = "Includes ₹25 savings through free delivery",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}

