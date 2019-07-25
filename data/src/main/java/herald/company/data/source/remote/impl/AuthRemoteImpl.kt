package herald.company.data.source.remote.impl

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import herald.company.data.common.extensions.loadJSONFromAsset
import herald.company.data.source.remote.AuthRemote
import herald.company.data.source.remote.client.AuthApiClient
import herald.company.data.source.remote.dto.AuthDto
import io.reactivex.Single
import timber.log.Timber


class AuthRemoteImpl constructor(
    private val context: Context,
    private val api: AuthApiClient
) : AuthRemote {

    override fun login(data: Map<String, Any>?): Single<AuthDto> {
        // return api.login(data)
        // Just for now mock the data using json file to get the list of user. You can see the json file in data module located in assets/json
        val usersString = loadJSONFromAsset(context, "users")
        val listType = object : TypeToken<MutableList<AuthDto>>() {}.type
        val users = Gson().fromJson<MutableList<AuthDto>>(usersString, listType)
        Timber.d("users: $users")
        val foundUser = users.find {
            data?.get("username") == it.username && data?.get("password") == it.password
        }
        return if (foundUser != null)
            Single.fromCallable { foundUser }
        else
            Single.error(Throwable("User not exist in the database."))
    }


}