package com.example.andromangler.domain.useCase

import com.example.andromangler.domain.item.WebsiteItem
import com.example.andromangler.repository.WebsiteRepository
import javax.inject.Inject

class GetWebsiteListUseCase @Inject constructor(private val websiteRepository: WebsiteRepository) {
    suspend operator fun invoke() : List<WebsiteItem>{
        return websiteRepository.getAll().shuffled().sortedBy { it.name }
    }
}