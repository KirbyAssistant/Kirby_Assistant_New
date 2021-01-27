package ren.imyan.kirby.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResItem(
    val title: String,
    val imageUrl: String,
    val tag: String,
    val type: String = "notype"
) : Parcelable