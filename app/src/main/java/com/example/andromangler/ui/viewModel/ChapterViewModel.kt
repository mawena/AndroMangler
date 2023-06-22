package com.example.andromangler.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andromangler.domain.item.ChapterItem
import com.example.andromangler.domain.useCase.GetChapterListByMangaUseCase
import com.example.andromangler.domain.useCase.GetChapterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(private val getChapterListByMangaUseCase: GetChapterListByMangaUseCase, private val getChapterUseCase: GetChapterUseCase) : ViewModel(){
    private val _chapterList = MutableStateFlow(emptyList<ChapterItem>())
    val chapterList : StateFlow<List<ChapterItem>> get() = _chapterList

    private val _chapter = MutableStateFlow(ChapterItem("", "", "", "", "", "", "", "", "", "", emptyList()))
    val chapter : StateFlow<ChapterItem> get() = _chapter

    fun getChapterListByManga(mangaToken: String){
        viewModelScope.launch(){
            try {
                val chapterList = getChapterListByMangaUseCase(mangaToken)
                _chapterList.value = chapterList
            }catch (_: Exception){}
        }
    }

    fun getChapter(chapterToken: String){
        viewModelScope.launch {
            try{
                val chapter = getChapterUseCase(chapterToken)
                _chapter.value = chapter
            }catch (_:Exception){}
        }
    }

}