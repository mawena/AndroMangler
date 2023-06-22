package com.example.andromangler.repository

import android.util.Log
import com.example.andromangler.data.remote.chapter.ChapterService
import com.example.andromangler.domain.item.ChapterItem
import com.example.andromangler.domain.item.toChapterItem
import javax.inject.Inject

class ChapterRepository @Inject constructor(private val chapterService: ChapterService) {
    suspend fun getByManga(mangaToken: String) : List<ChapterItem>{
        return chapterService.getByManga(mangaToken).map{it.toChapterItem()}.sortedByDescending { it.scraped_at }
    }

    suspend fun get(chapterToken : String) : ChapterItem{
        return chapterService.get(chapterToken).toChapterItem()
    }
}