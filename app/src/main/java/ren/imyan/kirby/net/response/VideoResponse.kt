package ren.imyan.kirby.net.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class VideoResponse(
    @Json(name = "imageUrl")
    val imageUrl: String = "",
    @Json(name = "link")
    val link: String = "",
    @Json(name = "title")
    val title: String = ""
)