package com.example.andromangler.domain.useCase

import com.example.andromangler.domain.item.MangaItem
import com.example.andromangler.repository.MangaRepository
import javax.inject.Inject

class GetMangaListByWebsiteUseCase @Inject constructor(private val mangaRepository: MangaRepository) {
    suspend operator fun invoke(websiteToken: String) : List<MangaItem>{
        return mangaRepository.getByWebsite(websiteToken).shuffled().sortedBy { it.name }
    }
}