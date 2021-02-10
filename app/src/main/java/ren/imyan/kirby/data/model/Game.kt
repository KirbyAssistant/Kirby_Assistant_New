package ren.imyan.kirby.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 13:26
 * @website https://imyan.ren
 */
data class Game(
    val title: String,
    val image: String,
    @SerializedName("download_link")
    val downloadLink: Map<String, String>,
    val tag: String
)