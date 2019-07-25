package herald.company.cleanapp.common.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.formatString(stringResId: Int, vararg args: Any?): String =
    String.format(getString(stringResId, *args))

fun Fragment.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}