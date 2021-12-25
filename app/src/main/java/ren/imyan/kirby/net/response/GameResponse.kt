package ren.imyan.kirby.net.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class GameResponse(
    @Json(name = "download_link")
    val downloadLink: Map<String, String>?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "tag")
    val tag: String?,
    @Json(name = "title")
    val title: String?
)
