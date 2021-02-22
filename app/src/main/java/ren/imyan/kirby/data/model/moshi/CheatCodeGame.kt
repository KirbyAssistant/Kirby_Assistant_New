package ren.imyan.kirby.data.model.moshi

import com.squareup.moshi.JsonClass

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-18 11:43
 * @website https://imyan.ren
 */
@JsonClass(generateAdapter = true)
data class CheatCodeGame(val title: String, val image: String, val tag: String)