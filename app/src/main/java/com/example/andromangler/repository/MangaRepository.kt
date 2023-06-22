package com.example.andromangler.repository

import com.example.andromangler.data.remote.manga.MangaService
import com.example.andromangler.domain.item.MangaItem
import com.example.andromangler.domain.item.toMangaItem
import javax.inject.Inject

class MangaRepository @Inject constructor(private val mangaService: MangaService) {
    suspend fun getAll() : List<MangaItem>{
        return mangaService.getAll().map{it.toMangaItem()}.sortedBy { it.name }
    }

    suspend fun getByWebsite(websiteToken: String) : List<MangaItem>{
        return mangaService.getByWebsite(websiteToken).map{it.toMangaItem()}.sortedBy { it.name }
    }
}