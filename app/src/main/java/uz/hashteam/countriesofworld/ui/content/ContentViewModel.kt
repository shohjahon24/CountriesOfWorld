package uz.hashteam.countriesofworld.ui.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.hashteam.countriesofworld.data.content.Content
import uz.hashteam.countriesofworld.repository.Repo

class ContentViewModel : ViewModel() {

    val data: MutableLiveData<List<Content>> = MutableLiveData()

    fun getContents(repo: Repo, isSort: Boolean = false) {
        viewModelScope.launch {
            data.value = repo.getContents(isSort)
        }
    }
}