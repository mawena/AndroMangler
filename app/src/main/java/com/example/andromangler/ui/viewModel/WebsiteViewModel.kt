package com.example.andromangler.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andromangler.domain.item.WebsiteItem
import com.example.andromangler.domain.useCase.GetWebsiteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebsiteViewModel @Inject constructor(private val getWebsiteListUseCase: GetWebsiteListUseCase): ViewModel(){
    private val _websiteList = MutableStateFlow(emptyList<WebsiteItem>())
    val websiteList : StateFlow<List<WebsiteItem>> get() = _websiteList

    init{
        getWebsiteList()
    }

    fun getWebsiteList(){
        viewModelScope.launch {
            try{
                val websiteList = getWebsiteListUseCase()
                _websiteList.value = websiteList
            } catch(_: Exception){}
        }
    }

}