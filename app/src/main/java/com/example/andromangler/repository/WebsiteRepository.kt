package com.example.andromangler.repository

import com.example.andromangler.data.remote.website.WebsiteService
import com.example.andromangler.domain.item.WebsiteItem
import com.example.andromangler.domain.item.toWebsiteItem
import javax.inject.Inject

class WebsiteRepository @Inject constructor(private val websiteService: WebsiteService){
    suspend fun getAll() : List<WebsiteItem>{
        return websiteService.getAll().map{it.toWebsiteItem()}.sortedBy { it.name }
    }
}