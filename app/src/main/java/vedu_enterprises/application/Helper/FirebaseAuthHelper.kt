package vedu_enterprises.application.Helper

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirebaseAuthHelper {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference
    private val storageReference = FirebaseStorage.getInstance().reference

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut() {
        auth.signOut()
    }

    fun deleteAccount() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                throw task.exception ?: Exception("Failed to delete account")
            }
        }
    }

    fun saveUserData(context: Context, userId: String, userData: Map<String, String?>) {
        database.child("users").child(userId).setValue(userData).addOnSuccessListener {
            Log.d("FirebaseDatabaseHelper", "User data saved successfully")
            // Utils.showToast(context, "Success")
        }.addOnFailureListener { e ->
            Log.e("FirebaseDatabaseHelper", "Error saving user data", e)
            // Utils.showToast(context, e.localizedMessage)
        }
    }

    fun uploadImage(context: Context, userId: String, imageUri: Uri, onSuccess: (String) -> Unit) {
        val imageRef = storageReference.child("users/$userId/profile_image.jpg")

        imageRef.putFile(imageUri).addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                //   Utils.showToast(context, "Success")
                onSuccess(uri.toString())
            }
        }.addOnFailureListener { e ->
            Log.e("FirebaseStorageHelper", "Error uploading image", e)
            //  Utils.showToast(context, e.localizedMessage)
        }
    }

}