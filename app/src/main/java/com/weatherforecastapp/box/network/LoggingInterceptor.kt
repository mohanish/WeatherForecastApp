package com.weatherforecastapp.box.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()
        Log.d(
            "Request",
            String.format(
                "Sending request %s on %s%n%s",
                request.url, chain.connection(), request.headers
            )
        )
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.d(
            "Response TIme",
            String.format(
                "Received response for %s in %.1fms%n%s",
                response.request.url, (t2 - t1) / 1e6, response.headers
            )
        )
        val responseString = String(response.body!!.bytes())
        Log.d("Response", "Response: $responseString")
        return response.newBuilder()
            .body(responseString.toResponseBody(response.body!!.contentType()))
            .build()
    }
}