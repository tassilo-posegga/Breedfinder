package eu.posegga.template.remote.provider

import android.content.Context
import eu.posegga.template.common.Provider
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

class OkHttpProvider(
    private val context: Context
) : Provider<OkHttpClient> {

    override fun provide(): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, CACHE_DIRECTORY), CACHE_SIZE))
            .build()

    private companion object {
        // 10 MB
        const val CACHE_SIZE: Long = 10 * 1024 * 1024
        const val CACHE_DIRECTORY = "http"
    }
}
