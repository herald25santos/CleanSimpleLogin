package herald.company.data.common

import android.content.Context
import herald.company.data.R
import java.io.IOException

class NoConnectivityException(context: Context) :
    IOException(context.getString(R.string.error_no_internet_connection))