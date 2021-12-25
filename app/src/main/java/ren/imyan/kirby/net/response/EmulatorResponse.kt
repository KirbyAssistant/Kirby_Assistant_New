package ren.imyan.kirby.net.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class EmulatorResponse(
    @Json(name = "image")
    val image: String = "",
    @Json(name = "tag")
    val tag: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "type")
    val type: String = ""
)