package com.erya.testvk.network

import com.erya.testvk.network.models.ResponseModel
import com.erya.testvk.network.models.ResponseSearchModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("random")
    suspend fun getRandomGiph() : ResponseModel

    @GET("search")
    suspend fun getSpecialGif(@Query("q") question: String) : ResponseSearchModel

    companion object {
        var retrofitService: GifApi? = null
        val client = OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor addApiKeyToRequests(chain) }
            .build()

        private fun addApiKeyToRequests(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "n3hF1liPyyk45JU6O94KicILC4dgtLqp").build()
            request.url(newUrl)
            return chain.proceed(request.build())
        }

        fun getInstance() : GifApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.giphy.com/v1/gifs/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(GifApi::class.java)
            }
            return retrofitService!!
        }

    }
}