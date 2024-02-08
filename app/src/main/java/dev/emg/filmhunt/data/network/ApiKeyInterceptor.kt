package dev.emg.filmhunt.data.network

import dev.emg.filmhunt.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

  override fun intercept(chain: Chain): Response {
    val original = chain.request()
    val authenticatedRequest = original.newBuilder()
      .addHeader("accept", "application/json")
      .addHeader("Authorization", "Bearer ${BuildConfig.THEMOVIEDB_API_KEY}")
      .build()
    return chain.proceed(authenticatedRequest)
  }

}