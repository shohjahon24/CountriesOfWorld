package uz.hashteam.countriesofworld.ui.capital

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.repository.Repo

class CapitalViewModel : ViewModel() {

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    private var data: List<Country> = ArrayList()

    fun getCountries(repo: Repo) {
        viewModelScope.launch {
            val d = repo.getCountries(sortByCapital = true)
            countries.value = d
            data = d
        }
    }

    fun search(q: String) {
        val result: ArrayList<Country> = ArrayList()
        if (q.isEmpty()){
            countries.value = data
            Log.d("AAA", "lllll")
        }
        else {
            data.forEach {
                if (it.capital.name.toLowerCase().contains(q.toLowerCase()))
                    result.add(it)
            }
            countries.value = result
        }
    }

}