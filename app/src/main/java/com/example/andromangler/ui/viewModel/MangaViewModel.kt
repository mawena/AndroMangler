package com.example.andromangler.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andromangler.domain.item.MangaItem
import com.example.andromangler.domain.useCase.GetMangaListByWebsiteUseCase
import com.example.andromangler.domain.useCase.GetMangaListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(private val getMangaListUseCase: GetMangaListUseCase, private val getMangaListByWebsiteUseCase: GetMangaListByWebsiteUseCase) : ViewModel(){
    private val _mangaList = MutableStateFlow(emptyList<MangaItem>())
    val mangaList : StateFlow<List<MangaItem>> get() = _mangaList

    fun getMangaList(){
        viewModelScope.launch {
            try{
                val mangaList = getMangaListUseCase()
                _mangaList.value = mangaList
            }catch(_: Exception){}
        }
    }

    fun getMangaListByWebsite(websiteToken : String){
        viewModelScope.launch {
            try{
                val mangaList = getMangaListByWebsiteUseCase(websiteToken)
                _mangaList.value = mangaList
            }catch(_: Exception){}
        }
    }
}