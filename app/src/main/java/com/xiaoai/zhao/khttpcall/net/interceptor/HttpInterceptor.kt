package com.xiaoai.zhao.khttpcall.net.interceptor

import android.util.Log
import com.xiaoai.zhao.khttpcall.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.HttpEngine
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit

class HttpInterceptor private constructor() : Interceptor {

    private val UTF8 = Charset.forName("UTF-8")
    private var header: String? = null

    fun setHeader(header: String) {
        this.header = header
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        //添加请求头
        val request = chain.request()
                .newBuilder()
                .addHeader("humpJsonStyle", "true")
                .build()

        val response = chain.proceed(request)
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            val requestBody = request.body()
            var body: String? = null
            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                var charset = UTF8
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(UTF8)
                }
                body = buffer.readString(charset)
            }
            val startNs = System.nanoTime()

            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            val responseBody = response.body()
            var rBody: String? = null
            if (HttpEngine.hasBody(response)) {
                val source = responseBody.source()
                source.request(java.lang.Long.MAX_VALUE)
                val buffer = source.buffer()
                var charset = UTF8
                val contentType = responseBody.contentType()
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8)
                    } catch (e: UnsupportedCharsetException) {
                        e.printStackTrace()
                    }

                }
                rBody = buffer.clone().readString(charset)
            }
            Log.e("XiaoAi", "收到响应 " + response.code() + " " + response.message() + "  \n耗时:" + tookMs +
                    "\nmethod:" + request.method() + "\nheaders: " + request.headers() + "  \n请求url:" + response.request().url() +
                    "\n请求body:" + body + "\n响应body：" + rBody + "\n")
        }
        return response
    }

    companion object {
        private var httpInterceptor: HttpInterceptor? = null
        val instance: HttpInterceptor
            @Synchronized get() {
                if (httpInterceptor == null) {
                    httpInterceptor = HttpInterceptor()
                }
                return httpInterceptor as HttpInterceptor
            }
    }
}