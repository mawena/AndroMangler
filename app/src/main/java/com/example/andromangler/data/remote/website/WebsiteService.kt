package com.example.andromangler.data.remote.website

import com.example.andromangler.data.remote.website.model.WebsiteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WebsiteService @Inject constructor(private val websiteApi: WebsiteApi){
    suspend fun getAll() : List<WebsiteModel>{
        return withContext(Dispatchers.IO){
            val actorArticles = websiteApi.getAll()
            actorArticles.body() ?: emptyList()
        }
    }
}