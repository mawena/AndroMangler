package com.example.andromangler.data.remote.manga

import com.example.andromangler.data.remote.manga.model.MangaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MangaService @Inject constructor(private val mangaApi: MangaApi) {
    suspend fun getAll(): List<MangaModel>{
        return withContext(Dispatchers.IO){
            val mangaList = mangaApi.getAll()
            mangaList.body() ?: emptyList()
        }
    }

    suspend fun getByWebsite(websiteToken:String): List<MangaModel>{
        return withContext(Dispatchers.IO){
            val mangaList = mangaApi.getByWebsite(websiteToken)
            mangaList.body() ?: emptyList()
        }
    }
}