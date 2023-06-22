package com.example.andromangler.data.remote.chapter.model

data class ChapterModel(
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