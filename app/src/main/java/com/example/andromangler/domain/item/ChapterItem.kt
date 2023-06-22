package com.example.andromangler.domain.item

import com.example.andromangler.data.remote.chapter.model.ChapterModel

data class ChapterItem (
    val token: String,
    val title: String,
    val link: String,
    val number: String,
    val scraped_at: String,
    val manga_name: String,
    val manga_token: String,
    val website_name: String,
    val website_token: String,
    val cover_page: String,
    val images: List<String>,
)

fun ChapterModel.toChapterItem() = ChapterItem(token, title, link, number, scraped_at, manga_name, manga_token, website_name, website_token, cover_page, images)