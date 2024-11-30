package vedu_enterprises.application.activityies.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import vedu_enterprises.application.Helper.FirebaseAuthHelper
import vedu_enterprises.application.ViewModels.AuthenticationViewModel
import vedu_enterprises.application.activityies.activity.ui.theme.VeduEnterprisesTheme
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.Utils

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authHelper = FirebaseAuthHelper()
            val authenticationViewModel: AuthenticationViewModel = viewModel()
            VeduEnterprisesTheme {
                ProfileScreen(authenticationViewModel, authHelper)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper) {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = { Text("Profile", fontSize = 18.sp) }, navigationIcon = {
            IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        })
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ProfileHeader()
            }
            item {
                SectionTitle("OTHER INFORMATION")
            }
            item {
                MenuItemRow(icon = Icons.Default.Share,
                    title = Constants.SHARE_APP,
                    onClick = { shareApp(context) })
                MenuItemRow(icon = Icons.Default.Info,
                    title = Constants.ABOUT_US,
                    onClick = { showAboutUs(context) })
                MenuItemRow(icon = Icons.Default.Notifications,
                    title = Constants.NOTIFICATION_PREFERENCES,
                    onClick = { openNotificationPreferences(context) })
                MenuItemRow(icon = Icons.Default.Logout,
                    title = Constants.LOG_OUT,
                    onClick = { logOut(viewModel, authHelper, context) })
                MenuItemRow(icon = Icons.Default.Shield,
                    title = Constants.PRIVACY_POLICY,
                    onClick = { showPrivacyPolicy(context) })
                MenuItemRow(icon = Icons.Default.Phone,
                    title = Constants.CONTACT_US,
                    onClick = { contactSupport(context) })
                MenuItemRow(icon = Icons.Default.Delete,
                    title = Constants.DELETE_ACCOUNT,
                    onClick = { deleteAccount(viewModel, authHelper, context) })
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Text(
        "My account",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        "9336267840", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        title,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}

@Composable
fun MenuItemRow(icon: ImageVector, title: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    Modifier.size(22.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(title, fontSize = 16.sp)
            }
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun shareApp(context: Context) {
    // Share app logic
}

fun showAboutUs(context: Context) {
    // Navigate to About Us
}

fun openNotificationPreferences(context: Context) {
    // Open notification preferences
}

fun logOut(viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper, context: Context) {
    viewModel.logOut(authHelper) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}

fun showPrivacyPolicy(context: Context) {
    // Show Privacy Policy
}

fun contactSupport(context: Context) {
    // Contact Support Logic
}

fun deleteAccount(
    viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper, context: Context
) {
    AlertDialog.Builder(context).apply {
        setTitle("Delete Account")
        setMessage("Are you sure you want to delete your account? This action cannot be undone.")
        setPositiveButton("Delete") { _, _ ->
            viewModel.deleteAccount(authHelper) {
                Utils.showToast(context, "Account deleted successfully")
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        }
        setNegativeButton("Cancel", null)
    }.show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    VeduEnterprisesTheme {
        ProfileScreen(
            AuthenticationViewModel(),
            FirebaseAuthHelper()
        )
    }
}