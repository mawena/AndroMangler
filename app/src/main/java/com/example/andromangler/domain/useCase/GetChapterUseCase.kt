package com.example.andromangler.domain.useCase

import com.example.andromangler.domain.item.ChapterItem
import com.example.andromangler.repository.ChapterRepository
import javax.inject.Inject

class GetChapterUseCase @Inject constructor(private val chapterRepository: ChapterRepository) {
    suspend operator fun invoke(chapterToken: String) : ChapterItem{
        return chapterRepository.get(chapterToken)
    }
}