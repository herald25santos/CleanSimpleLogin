package herald.company.data.source.local

import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.securepreferences.SecurePreferences
import herald.company.data.BuildConfig

/**
 * Created by herald25santos on 2019-07-24
 */

class RxSharedPreferencesUtil(
    context: Context
) {

    private var rxSharedPreferences: RxSharedPreferences

    init {
        val preferences = SecurePreferences(context, "", "autobahn_prefs.xml")
        if (BuildConfig.DEBUG) {
            SecurePreferences.setLoggingEnabled(true)
        } else {
            SecurePreferences.setLoggingEnabled(false)
        }
        rxSharedPreferences = RxSharedPreferences.create(preferences)
    }

    private val isLoggedInPref = "isLoggedIn"

    private val fullNamePref = "fullName"

    fun isLoggedIn() = rxSharedPreferences.getBoolean(isLoggedInPref, false)

    fun fullName() = rxSharedPreferences.getString(fullNamePref)

}