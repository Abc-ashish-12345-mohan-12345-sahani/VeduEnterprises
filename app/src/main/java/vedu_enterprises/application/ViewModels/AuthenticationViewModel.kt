package vedu_enterprises.application.ViewModels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.launch
import vedu_enterprises.application.Helper.FirebaseAuthHelper
import vedu_enterprises.application.ui.theme.Constants

class AuthenticationViewModel() : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage

    private val _user = mutableStateOf<FirebaseUser?>(null)
    val user: State<FirebaseUser?> get() = _user

    fun signIn(
        email: String,
        password: String,
        authHelper: FirebaseAuthHelper,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val user = authHelper.signInWithEmailPassword(email, password)
                if (user != null) {
                    _user.value = user
                    onSuccess()
                } else {
                    _errorMessage.value = "Invalid credentials or user does not exist"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun signUp(
        email: String, password: String,
        authHelper: FirebaseAuthHelper, onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val user = authHelper.signUpWithEmailPassword(email, password)
                if (user != null) {
                    _user.value = user
                    onSuccess()
                } else {
                    _errorMessage.value = "Sign-up failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logOut(authHelper: FirebaseAuthHelper, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                authHelper.signOut()
                _user.value = null
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to log out"
            }
        }
    }

    fun deleteAccount(authHelper: FirebaseAuthHelper, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                authHelper.deleteAccount()
                _user.value = null
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to delete account"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveUserData(
        context: Context,
        name: String,
        phoneNumber: String,
        authHelper: FirebaseAuthHelper
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val currentUser = _user.value
                if (currentUser != null) {
                    val userId = currentUser.uid
                    val userData = mapOf(
                        "name" to name,
                        "phoneNumber" to phoneNumber,
                        "email" to currentUser.email
                    )
                    authHelper.saveUserData(context, userId, userData)
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "No user is logged in"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to save user data"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun uploadUserImage(
        context: Context,
        imageUri: Uri,
        authHelper: FirebaseAuthHelper,
        onSuccess: (String) -> Unit, onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val currentUser = _user.value
                if (currentUser != null) {
                    val userId = currentUser.uid
                    authHelper.uploadImage(context, userId, imageUri) { imageUrl ->
                        onSuccess(imageUrl)
                        onError("Error")
                    }
                } else {
                    _errorMessage.value = "No user is logged in"
                    Prefs.getString(Constants.USER_IMAGE, imageUri.toString())
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to upload user image"
            } finally {
                _isLoading.value = false
                Prefs.getString(Constants.USER_IMAGE, imageUri.toString())
            }
        }
    }
}

