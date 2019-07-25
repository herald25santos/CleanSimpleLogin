package herald.company.cleanapp.di

import android.content.Context
import herald.company.cleanapp.BuildConfig
import herald.company.cleanapp.R
import herald.company.data.source.remote.client.AuthApiClient
import herald.company.data.source.remote.interceptor.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class BasicAuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url().newBuilder().build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

}

fun createHttpClient(context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder().apply {
        //addInterceptor(RequestInterceptor(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET))
        addInterceptor(BasicAuthInterceptor())
        addInterceptor(ConnectivityInterceptor(context))
        readTimeout(
            context.resources.getInteger(R.integer.time_api_request).toLong(),
            TimeUnit.SECONDS
        )
        writeTimeout(
            context.resources.getInteger(R.integer.time_api_request).toLong(),
            TimeUnit.SECONDS
        )
    }
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    return clientBuilder.build()
}

inline fun <reified T> createRetrofitClient(baseUrl: String, httpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}