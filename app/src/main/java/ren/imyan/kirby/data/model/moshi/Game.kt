package ren.imyan.kirby.data.model.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 13:26
 * @website https://imyan.ren
 */
@JsonClass(generateAdapter = true)
data class Game(
    val title: String,
    val image: String,
    @Json(name = "download_link")
    val downloadLink: Map<String, String>,
    val tag: String
)