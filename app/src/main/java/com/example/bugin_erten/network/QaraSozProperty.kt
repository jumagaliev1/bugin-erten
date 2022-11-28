package com.example.bugin_erten.network

import com.squareup.moshi.Json

data class QaraSozProperty(
    val id: String,
    val title: String,
    val text: String,
    @Json(name = "imageSrc") val imgSrcUrl: String
)