package com.example.andromangler.domain.useCase

import com.example.andromangler.domain.item.MangaItem
import com.example.andromangler.repository.MangaRepository
import javax.inject.Inject

class GetMangaListUseCase @Inject constructor(private val mangaRepository: MangaRepository) {
    suspend operator fun invoke(): List<MangaItem>{
        return mangaRepository.getAll().shuffled().sortedBy { it.name }
    }
}