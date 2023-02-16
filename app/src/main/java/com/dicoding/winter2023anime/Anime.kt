package com.dicoding.winter2023anime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    var title: String,
    var synopsis: String,
    var cover: String,
    var release: String,
    var engTitle: String,
    var episodes: Int,
    var rating: String
) : Parcelable
