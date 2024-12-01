package vedu_enterprises.application.activityies.activity

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pixplicity.easyprefs.library.Prefs
import vedu_enterprises.application.Helper.FirebaseAuthHelper
import vedu_enterprises.application.R
import vedu_enterprises.application.ViewModels.AuthenticationViewModel
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.Utils
import vedu_enterprises.application.ui.theme.VeduEnterprisesTheme

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
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        DeleteAccountDialog(onDismiss = { showDialog.value = false }, onConfirm = {
            showDialog.value = false
            viewModel.deleteAccount(authHelper) {
                Utils.showToast(context, "Account deleted successfully")
                val intent = Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                context.startActivity(intent)
            }
        })
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.profile), fontSize = 18.sp) },
            navigationIcon = {
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
            item { ProfileHeader() }
            item { SectionTitle(stringResource(R.string.other_information)) }
            item {
                MenuItemRow(
                    icon = Icons.Default.Share,
                    stringResource(R.string.share_app),
                    onClick = {})
            }
            item {
                MenuItemRow(
                    icon = Icons.Default.Info,
                    stringResource(R.string.about_us),
                    onClick = {})
            }
            item {
                MenuItemRow(icon = Icons.Default.Notifications,
                    stringResource(R.string.notification_preferences),
                    onClick = { })
            }
            item {
                MenuItemRow(icon = Icons.Default.Logout,
                    title = stringResource(R.string.logout),
                    onClick = { logOut(viewModel, authHelper, context) })
            }
            item {
                MenuItemRow(
                    icon = Icons.Default.Shield,
                    stringResource(R.string.privacy_policy),
                    onClick = { })
            }
            item {
                MenuItemRow(
                    icon = Icons.Default.Phone,
                    stringResource(R.string.contact_us),
                    onClick = { })
            }
            item {
                MenuItemRow(icon = Icons.Default.Delete,
                    stringResource(R.string.delete_account),
                    onClick = { deleteAccount(showDialog) })
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Text(
        stringResource(R.string.my_account),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        Prefs.getString(Constants.PHONE).ifEmpty { "No phone number available" },
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f),
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(imageVector = icon, contentDescription = null, Modifier.padding(8.dp))
                }
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

fun logOut(viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper, context: Context) {
    viewModel.logOut(authHelper) {
        val intent = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
}

fun deleteAccount(showDialog: MutableState<Boolean>) {
    showDialog.value = true
}

@Composable
fun DeleteAccountDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(
            stringResource(R.string.delete_account),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }, text = {
        Text(
            stringResource(R.string.are_you_sure_you_want_to_delete_your_account_this_action_cannot_be_undone),
            fontSize = 16.sp
        )
    }, confirmButton = {
        TextButton(onClick = onConfirm) {
            Text("Delete", color = MaterialTheme.colorScheme.error)
        }
    }, dismissButton = {
        TextButton(onClick = onDismiss) {
            Text("Cancel", color = MaterialTheme.colorScheme.onSurface)
        }
    }, icon = {
        Icon(
            Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(40.dp)
        )
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    VeduEnterprisesTheme {
        ProfileScreen(AuthenticationViewModel(), FirebaseAuthHelper())
    }
}