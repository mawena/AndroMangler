package com.example.andromangler.data.remote.chapter

import android.util.Log
import com.example.andromangler.data.remote.chapter.model.ChapterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChapterService @Inject constructor(private val chapterApi: ChapterApi) {
    suspend fun getByManga(mangaToken: String): List<ChapterModel>{
        return withContext(Dispatchers.IO){
            val chapterList = chapterApi.getByManga(mangaToken)
            chapterList.body() ?: emptyList()
        }
    }

    suspend fun get(chapterToken: String): ChapterModel{
        return withContext(Dispatchers.IO){
            val chapter = chapterApi.get(chapterToken)
            chapter.body()!!
        }
    }
}