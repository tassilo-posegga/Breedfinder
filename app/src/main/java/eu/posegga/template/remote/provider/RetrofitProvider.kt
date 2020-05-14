package eu.posegga.template.remote.provider

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import eu.posegga.template.common.Provider
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitProvider(
    private val client: OkHttpClient
) : Provider<Retrofit> {

    override fun provide(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                Json(
                    JsonConfiguration.Stable.copy(
                        ignoreUnknownKeys = true,
                        encodeDefaults = false
                    )
                ).asConverterFactory(CONTENT_TYPE_JSON.toMediaType())
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private companion object {
        const val BASE_URL = "https://dog.ceo/api/"
        const val CONTENT_TYPE_JSON = "application/json"
    }
}
