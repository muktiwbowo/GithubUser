package m.wb.githubuser.service

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
            requestBuilder.addHeader("Accept", "application/vnd.github.text-match+json")
            requestBuilder.addHeader(
                "Authorization",
                "Bearer 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"
            )
            chain.proceed(requestBuilder.build())
        }
    }
    return Retrofit.Builder().baseUrl("https://api.github.com/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(API::class.java)
}

interface API {
    @GET("search/users")
    suspend fun getUsersByUsername(
        @Query("q") username: String,
        @Query("per_page") perPage: Int
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