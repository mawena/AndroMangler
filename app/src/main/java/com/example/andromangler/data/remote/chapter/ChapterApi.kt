package com.example.andromangler.data.remote.chapter

import com.example.andromangler.data.remote.chapter.model.ChapterModel
import com.example.andromangler.utils.Constants.Companion.CHAPTER_ENDPOINT
import com.example.andromangler.utils.Constants.Companion.MANGA_CHAPTER_LIST_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChapterApi {
    @GET(MANGA_CHAPTER_LIST_ENDPOINT)
    suspend fun getByManga(@Query("manga_token") mangaToken: String): Response<List<ChapterModel>>

    @GET(CHAPTER_ENDPOINT)
    suspend fun get(@Path("chapter_token") chapterToken: String): Response<ChapterModel>
}