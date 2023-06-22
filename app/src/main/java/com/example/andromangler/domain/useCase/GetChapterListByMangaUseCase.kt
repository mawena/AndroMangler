package com.example.andromangler.domain.useCase

import com.example.andromangler.domain.item.ChapterItem
import com.example.andromangler.repository.ChapterRepository
import javax.inject.Inject

class GetChapterListByMangaUseCase @Inject constructor(private val chapterRepository: ChapterRepository) {
    suspend operator fun invoke(mangaToken: String): List<ChapterItem>{
        return chapterRepository.getByManga(mangaToken).shuffled().sortedByDescending { it.number }
    }
}