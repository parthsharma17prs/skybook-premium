package com.skybook.api

import com.skybook.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
  COMPUTER NETWORKS ANNOTATIONS:

  CLIENT-SERVER MODEL:
  Android = Client (initiates all requests, variable IP)
  ASP.NET = Server (passive listener, fixed IP/domain, port 443)

  HTTP REQUEST LIFECYCLE:
  1. DNS resolution: skybook.api.com → 203.x.x.x
  2. TCP 3-way handshake: SYN → SYN-ACK → ACK
  3. TLS handshake: exchange certificates, agree cipher suite
  4. HTTP/1.1 or HTTP/2 request: verb + headers + JSON body
  5. Server: deserializes JSON → business logic → serializes response
  6. HTTP response: status code + headers + JSON body
  7. Keep-Alive: connection reused for next request

  REST CONSTRAINTS APPLIED:
  - Stateless: JWT carries all auth state, no server sessions
  - Uniform interface: standard HTTP verbs + status codes
  - Layered: client unaware of DB, cache, SignalR layers
  - Cache: GET endpoints return Cache-Control headers

  JSON TRANSFER:
  Serialization   = C# object  → JSON string (System.Text.Json)
  Transmission    = JSON bytes over TCP/IP
  Deserialization = JSON string → Kotlin data class (Gson)

  SIGNALR (WebSocket upgrade):
  HTTP GET /signalr/negotiate → returns connection token
  WebSocket handshake upgrade → persistent full-duplex channel
  Server PUSHES seat updates without client polling
*/

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(authInterceptor: AuthInterceptor): ApiService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!.create(ApiService::class.java)
    }
}
