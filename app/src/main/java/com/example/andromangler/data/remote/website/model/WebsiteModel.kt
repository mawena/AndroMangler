package com.example.andromangler.data.remote.website.model

import com.example.andromangler.data.remote.manga.model.MangaModel

data class WebsiteModel(
    val token: String,
    val name: String,
    val slug: String,
    val link: String,
    val mangas: List<MangaModel>,
)