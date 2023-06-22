package com.example.andromangler.domain.item

import com.example.andromangler.data.remote.website.model.WebsiteModel

data class WebsiteItem (
    val token: String,
    val name: String,
    val slug: String,
    val link: String,
    val mangas: List<MangaItem>
)

fun WebsiteModel.toWebsiteItem() = WebsiteItem(token, name, slug, link, mangas.map{it.toMangaItem()})