package herald.company.data.common.extensions

import android.content.Context
import java.io.IOException

fun String?.nullable(): String {
    return this ?: ""
}

fun loadJSONFromAsset(context: Context, file: String): String {
    val json: String?
    try {
        val inputStream = context.assets.open("json/$file.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer)
    } catch (ex: IOException) {
        return ""
    }

    return json
}