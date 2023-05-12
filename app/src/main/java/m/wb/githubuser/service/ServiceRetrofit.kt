package m.wb.githubuser.service

import m.wb.githubuser.BuildConfig
import m.wb.githubuser.data.BaseData
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun instance(): API {
    val client = OkHttpClient.Builder().addNetworkInterceptor { chain ->
        with(chain.request()) {
            val requestBuilder = newBuilder().method(method, body)
            requestBuilder.addHeader("Accept", BuildConfig.HEADER_ACCEPT)
            requestBuilder.addHeader(
                "Authorization",
                "Bearer ${BuildConfig.ACCESS_TOKEN}"
            )
            chain.proceed(requestBuilder.build())
        }
    }
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(API::class.java)
}

interface API {
    @GET("search/users")
    suspend fun getUsersByUsername(
        @Query("q") username: String
    ): Response<BaseData>
}

sealed class Status<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = 0
) {
    class Loading<T> : Status<T>()
    class Success<T>(data: T?) : Status<T>(data = data)
    class Error<T>(message: String) : Status<T>(message = message)
}