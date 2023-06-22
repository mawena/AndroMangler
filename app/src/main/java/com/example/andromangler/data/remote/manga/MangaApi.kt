package com.example.andromangler.data.remote.manga

import com.example.andromangler.data.remote.manga.model.MangaModel
import com.example.andromangler.utils.Constants.Companion.MANGA_LIST_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApi {
    @GET(MANGA_LIST_ENDPOINT)
    suspend fun getAll(): Response<List<MangaModel>>


    @GET(MANGA_LIST_ENDPOINT)
    suspend fun getByWebsite(@Query("website_token") websiteToken: String): Response<List<MangaModel>>
}