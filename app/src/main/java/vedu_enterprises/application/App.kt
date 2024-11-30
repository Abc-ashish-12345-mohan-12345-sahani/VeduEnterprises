package vedu_enterprises.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.pixplicity.easyprefs.library.Prefs

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Prefs.Builder().setContext(this).build()
    }
}