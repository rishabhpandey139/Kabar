package com.example.limitlesstech.limitlessnews.core.network

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import com.example.limitlesstech.limitlessnews.BuildConfig

@Singleton
class CloudinaryUploader @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    companion object {

        private const val TAG = "CloudinaryUploader"

        private val CLOUD_NAME =
            BuildConfig.CLOUDINARY_CLOUD_NAME

        private val UPLOAD_PRESET =
            BuildConfig.CLOUDINARY_UPLOAD_PRESET

        private val BASE_URL =
            "https://api.cloudinary.com/v1_1/$CLOUD_NAME/image/upload"
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun uploadImage(
        imageUri: Uri
    ): String = withContext(Dispatchers.IO) {

        Log.d(TAG, "Upload started")

        val file = uriToFile(imageUri)

        Log.d(TAG, "Uploading file: ${file.absolutePath}")

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "upload_preset",
                UPLOAD_PRESET
            )
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )
            .build()

        val request = Request.Builder()
            .url(BASE_URL)
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->

            val responseBody = response.body?.string()

            Log.d(TAG, "HTTP Code = ${response.code}")
            Log.d(TAG, "Response = $responseBody")

            if (!response.isSuccessful) {
                throw Exception(
                    "Cloudinary Error (${response.code})\n$responseBody"
                )
            }

            if (responseBody.isNullOrBlank()) {
                throw Exception("Empty response from Cloudinary")
            }

            val json = JSONObject(responseBody)

            if (!json.has("secure_url")) {
                throw Exception(
                    json.optJSONObject("error")
                        ?.optString("message")
                        ?: "secure_url not found"
                )
            }

            json.getString("secure_url")
        }
    }

    private fun uriToFile(
        uri: Uri
    ): File {

        val resolver: ContentResolver =
            context.contentResolver

        val fileName = getFileName(uri)

        val file = File(
            context.cacheDir,
            fileName
        )

        resolver.openInputStream(uri)?.use { input ->

            FileOutputStream(file).use { output ->

                input.copyTo(output)

            }

        } ?: throw Exception("Unable to read selected image")

        return file
    }

    private fun getFileName(
        uri: Uri
    ): String {

        val resolver = context.contentResolver

        resolver.query(
            uri,
            null,
            null,
            null,
            null
        )?.use { cursor ->

            val index =
                cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)

            if (index != -1 && cursor.moveToFirst()) {

                return cursor.getString(index)

            }
        }

        return "profile_image.jpg"
    }
}