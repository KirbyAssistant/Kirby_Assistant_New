package ren.imyan.kirby.data.model.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 12:11
 * @website https://imyan.ren
 */
@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "title")
    val title: String,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "link")
    val link: String
)
