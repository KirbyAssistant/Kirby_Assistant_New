package ren.imyan.kirby.net.response

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@Keep
@JsonClass(generateAdapter = true)
data class ConsoleResponse(
    @Json(name = "image")
    val image: String = "",
    @Json(name = "tag")
    val tag: String = "",
    @Json(name = "title")
    val title: String = ""
)