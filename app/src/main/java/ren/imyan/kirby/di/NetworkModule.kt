package ren.imyan.kirby.di

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

// 超时时间
private const val DEFAULT_TIMEOUT = 15L

// 最大连接数
private const val MAX_LIMIT_CONNECTIONS = 10


val networkModule = module {
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.imyan.ren/ka/data/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectionPool(
                ConnectionPool(
                    MAX_LIMIT_CONNECTIONS,
                    DEFAULT_TIMEOUT,
                    TimeUnit.SECONDS
                )
            )
            .build()
    }
}