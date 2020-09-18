package uz.hashteam.countriesofworld.ui.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.repository.Repo

class CountryViewModel : ViewModel() {

    val countries: MutableLiveData<List<Country>> = MutableLiveData()
    private var data: List<Country> = ArrayList()

    fun getCountries(repo: Repo) {
        viewModelScope.launch {
            val d = repo.getCountries()
            countries.value = d
            data = d
            Log.d("AAA", "${d.size}")
        }
    }

    fun search(q: String) {
        val result: ArrayList<Country> = ArrayList()
        if (q.isEmpty())
            countries.value = data
        else {
            data.forEach {
                if (it.name.toLowerCase().contains(q.toLowerCase()))
                    result.add(it)
            }
            countries.value = result
        }
    }
}