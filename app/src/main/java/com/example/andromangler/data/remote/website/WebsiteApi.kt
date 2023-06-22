package com.example.andromangler.data.remote.website

import com.example.andromangler.data.remote.website.model.WebsiteModel
import com.example.andromangler.utils.Constants.Companion.WEBSITE_LIST_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface WebsiteApi {
    @GET(WEBSITE_LIST_ENDPOINT)
    suspend fun getAll(): Response<List<WebsiteModel>>
}