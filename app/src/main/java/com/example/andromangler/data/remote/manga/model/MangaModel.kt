package com.example.andromangler.data.remote.manga.model

import com.example.andromangler.data.remote.website.model.WebsiteModel

data class MangaModel(
    val token: String,
    val name: String,
    val slug: String,
    val gender: String,
    val description: String,
    val cover_page: String,
    val websites: List<WebsiteModel>
)