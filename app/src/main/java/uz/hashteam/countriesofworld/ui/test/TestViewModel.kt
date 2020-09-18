package uz.hashteam.countriesofworld.ui.test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.hashteam.countriesofworld.data.test.Test
import uz.hashteam.countriesofworld.repository.Repo

class TestViewModel : ViewModel() {

    val test: MutableLiveData<Test> = MutableLiveData()
    val count: MutableLiveData<Int> = MutableLiveData()
    private var data: List<Test> = ArrayList()
    private var trueAnswerCount = 0

    fun getTests(repo: Repo) {
        viewModelScope.launch {
            data = repo.getTests().shuffled().subList(0, 20)
            test.value = data[0]
        }
    }

    fun goNext(pos: Int, answer: String) {
        Log.d("AAA", "$pos")
        if (pos == 20) {
            count.value = trueAnswerCount
        } else {
            if (answer == data[pos - 1].answer)
                trueAnswerCount++
            test.value = data[pos]
        }
    }
}