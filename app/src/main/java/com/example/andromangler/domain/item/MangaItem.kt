package com.example.andromangler.domain.item

import com.example.andromangler.data.remote.manga.model.MangaModel

data class MangaItem (
    val token: String,
    val name: String,
    val slug: String,
    val gender: String,
    val description: String,
    val cover_page: String,
)

fun MangaModel.toMangaItem() = MangaItem(token, name, slug, gender, description, cover_page)