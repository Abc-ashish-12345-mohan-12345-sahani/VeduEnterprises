package vedu_enterprises.application.activityies.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.pixplicity.easyprefs.library.Prefs
import vedu_enterprises.application.Helper.FirebaseAuthHelper
import vedu_enterprises.application.R
import vedu_enterprises.application.ViewModels.AuthenticationViewModel
import vedu_enterprises.application.ui.theme.Constants
import vedu_enterprises.application.ui.theme.Gray10
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
                ProfileScreen(authenticationViewModel, authHelper, this)
            }
        }
    }
}

@Composable
fun ProfileScreen(
    viewModel: AuthenticationViewModel,
    authHelper: FirebaseAuthHelper,
    activity: ComponentActivity
) {
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
        ShowHeader(heading = "Profile", activity = activity)
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { ProfileHeader() }
            item { ShowCard(context, viewModel, authHelper) }
            item { SectionTitle(stringResource(R.string.other_information)) }
            item {
                MenuItemRow(icon = Icons.Default.Share,
                    stringResource(R.string.share_app),
                    onClick = {})
            }
            item {
                MenuItemRow(icon = Icons.Default.Info,
                    stringResource(R.string.about_us),
                    onClick = {})
            }
            item {
                MenuItemRow(icon = Icons.Default.Notifications,
                    stringResource(R.string.notification_preferences),
                    onClick = { })
            }
            item {
                MenuItemRow(icon = Icons.AutoMirrored.Filled.Logout,
                    title = stringResource(R.string.logout),
                    onClick = { logOut(viewModel, authHelper, context) })
            }
            item {
                MenuItemRow(icon = Icons.Default.Shield,
                    stringResource(R.string.privacy_policy),
                    onClick = { })
            }
            item {
                MenuItemRow(icon = Icons.Default.Phone,
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
}

@Composable
fun SectionTitle(title: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        title, fontSize = 14.sp, color = Color.Black, modifier = Modifier.padding(vertical = 10.dp)
    )
}

@Composable
fun MenuItemRow(icon: ImageVector, title: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
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
                Text(title, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
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
        ProfileScreen(
            AuthenticationViewModel(),
            FirebaseAuthHelper(),
            activity = object : ComponentActivity() {})
    }
}

@Composable
fun ShowCard(context: Context, viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
        viewModel.uploadUserImage(context, uri!!, authHelper, onSuccess = {
            Log.d("ShowCard", "Image uploaded successfully: $it")
        }, onError = {
            Log.e("ShowCard", "Image upload failed: $it")
        })
    }
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray10
        ), border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(painter = rememberAsyncImagePainter(
                model = imageUri.value ?: R.drawable.baseline_person_24
            ),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    }
                    .let { modifier ->
                        if (imageUri.value == null) {
                            modifier.padding(4.dp)
                        } else {
                            modifier
                        }
                    },
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {
                TextView(text = Prefs.getString(Constants.USERNAME))
                TextView(text = Prefs.getString(Constants.PHONE))
            }
        }
    }
}

@Composable
fun TextView(text: String) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 8.dp)
    )
}