package vedu_enterprises.application.activityies.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authHelper = FirebaseAuthHelper()
            val authenticationViewModel: AuthenticationViewModel = viewModel()
            VeduEnterprisesTheme {
                val context = LocalContext.current
                ModernLoginPage(context, authenticationViewModel, authHelper)
            }
        }
    }
}

@Composable
fun ModernLoginPage(
    context: Context, viewModel: AuthenticationViewModel, authHelper: FirebaseAuthHelper
) {
    var isSignIn by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value
    val user = viewModel.user.value

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Utils.showToast(context, it)
        }
    }

    val backgroundColor = Utils.getRandomLightColor()
    val surfaceColor = Color.White

    Surface(
        modifier = Modifier.fillMaxSize(), color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isSignIn) stringResource(R.string.welcome_back) else stringResource(R.string.create_account),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = surfaceColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Username
                    AnimatedVisibility(
                        visible = !isSignIn,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text(stringResource(R.string.username)) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = "User Name")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                            )
                        )
                    }
                    // User Phone Number
                    AnimatedVisibility(
                        visible = !isSignIn,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text(stringResource(R.string.phone_number)) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            leadingIcon = {
                                Icon(Icons.Default.Phone, contentDescription = "Phone Number")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // User Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.email)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = "Email")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Password
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = "Password")
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                        )
                    )
                    // Confirm Password
                    AnimatedVisibility(
                        visible = !isSignIn,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        // Confirm Password
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(stringResource(R.string.confirm_password)) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            leadingIcon = {
                                Icon(Icons.Default.Lock, contentDescription = "Confirm Password")
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    confirmPasswordVisible = !confirmPasswordVisible
                                }) {
                                    Icon(
                                        if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            },
                            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(30.dp), color = Color.Red
                        )
                    } else {
                        Button(
                            onClick = {
                                if (validateFields(
                                        email,
                                        password,
                                        confirmPassword,
                                        username,
                                        phoneNumber,
                                        isSignIn,
                                        context
                                    )
                                ) {
                                    if (isSignIn) {
                                        viewModel.signIn(email, password, authHelper) {
                                            context.startActivity(
                                                Intent(
                                                    context, MainActivity::class.java
                                                )
                                            )
                                            Prefs.putString(Constants.USER, user.toString())
                                            Prefs.putString(Constants.USERNAME, username)
                                            Prefs.putString(Constants.PHONE, phoneNumber)
                                            Utils.showToast(
                                                context,
                                                context.getString(R.string.welcome, username)
                                            )
                                            Prefs.putBoolean(Constants.IS_LOGGED_IN, true)
                                        }
                                    } else {
                                        viewModel.signUp(email, password, authHelper) {
                                            Utils.showToast(
                                                context,
                                                context.getString(R.string.sign_up_successful_please_log_in)
                                            )
                                            isSignIn = true
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {
                            Text(
                                text = if (isSignIn) stringResource(R.string.sign_in) else stringResource(
                                    R.string.sign_up
                                ), color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    TextButton(
                        onClick = { isSignIn = !isSignIn },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            if (isSignIn) stringResource(R.string.need_an_account_sign_up)
                            else stringResource(R.string.already_have_an_account_sign_in),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VeduEnterprisesTheme {
        ModernLoginPage(LocalContext.current, AuthenticationViewModel(), FirebaseAuthHelper())
    }
}

private fun validateFields(
    email: String,
    password: String,
    confirmPassword: String,
    username: String,
    phoneNumber: String,
    isSignIn: Boolean,
    context: Context
): Boolean {
    if (!isSignIn) {
        if (username.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.username_can_t_be_empty))
            return false
        } else if (phoneNumber.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.phone_number_can_t_be_empty))
            return false
        } else if (phoneNumber.length < 10) {
            Utils.showToast(context, context.getString(R.string.phone_number_should_be_10_digit))
            return false
        } else if (email.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.email_can_t_be_empty))
            return false
        } else if (password.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.password_can_t_be_empty))
            return false
        } else if (password.length < 6) {
            Utils.showToast(context, context.getString(R.string.password_must_be_6_digit))
            return false
        } else if (confirmPassword.isEmpty()) {
            Utils.showToast(
                context, context.getString(R.string.confirm_password_can_t_be_empty)
            )
            return false
        } else if (password != confirmPassword) {
            Utils.showToast(
                context, context.getString(R.string.password_and_confirm_password_must_be_same)
            )
            return false
        }
    } else {
        if (email.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.email_can_t_be_empty))
            return false
        } else if (password.isEmpty()) {
            Utils.showToast(context, context.getString(R.string.password_can_t_be_empty))
            return false
        } else if (password.length < 6) {
            Utils.showToast(context, context.getString(R.string.password_must_be_6_digit))
            return false
        }
    }
    return true
}