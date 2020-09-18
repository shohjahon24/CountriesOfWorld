package uz.hashteam.countriesofworld.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.hashteam.countriesofworld.data.capital.Capital
import uz.hashteam.countriesofworld.data.content.Content
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.data.test.Test
import uz.hashteam.countriesofworld.util.getJsonDataFromAsset

class Repo(private val context: Context) {
    suspend fun getContents(sortByCapital: Boolean = false): List<Content> {
        val contents: ArrayList<Content> = ArrayList()
        val countries = getCountries(false)
        (context.resources.assets.list("description_html"))?.forEachIndexed { i, it ->
            if (i < 20)
                contents.add(Content(i, countries[i].capital.id, it))
        }
        if (sortByCapital)
          return contents.sortedWith(compareBy { it.capitalId })
        return contents
    }

    suspend fun getCountries(sortByCapital: Boolean = false): List<Country> {
        val countries: ArrayList<Country> = ArrayList()
        countries.add(Country(0, Capital(16, "Qabul"), "Afg'oniston", "afghanistan"))
        countries.add(Country(1, Capital(17, "Tiran"), "Albaniya", "albania"))
        countries.add(Country(2, Capital(0, "Algeries"), "Jazoir", "algeria"))
        countries.add(Country(3, Capital(1, "Andorra la Vella"), "Andorra", "andorra"))
        countries.add(Country(4, Capital(9, "Luanda"), "Angola", "angola"))
        countries.add(Country(5, Capital(12, "Muqaddas Jon's"), "Antigua va Barbuda", "antigua"))
        countries.add(Country(6, Capital(6, "Buenos-Ayres"), "Argentina", "argentina"))
        countries.add(Country(7, Capital(19, "Yerevan"), "Armaniston", "armenia"))
        countries.add(Country(8, Capital(14, "Oranjestat"), "Aruba", "aruba"))
        countries.add(Country(9, Capital(7, "Canberra"), "Avstraliya", "australia"))
        countries.add(Country(10, Capital(18, "Vena"), "Avstriya", "austria"))
        countries.add(Country(11, Capital(3, "Boku"), "Ozarbayjon", "azerbaijan"))
        countries.add(Country(12, Capital(13, "Nassau"), "Bahamas", "bahamas"))
        countries.add(Country(13, Capital(10, "Manama"), "Bahrayn", "bahrain"))
        countries.add(Country(14, Capital(8, "Dhaka"), "Bangladesh", "bangladesh"))
        countries.add(Country(15, Capital(4, "Bridgetown"), "Barbados", "barbados"))
        countries.add(Country(16, Capital(11, "Minsk"), "Belarusiya", "belarus"))
        countries.add(Country(17, Capital(5, "Bryussel"), "Belgiya", "belgium"))
        countries.add(Country(18, Capital(2, "Belmopan"), "Beliz", "belize"))
        countries.add(Country(19, Capital(15, "Porto-Novo"), "Benin", "benin"))
        if (sortByCapital) {
           return countries.sortedWith(compareBy({ it.capital.name }, { it.capital.id }))
        }
        return countries
    }

    suspend fun getTests(): List<Test> {
        val gson = Gson()
        val jsonDataString = getJsonDataFromAsset(context, "quiz.json")
        return gson.fromJson(jsonDataString, object : TypeToken<ArrayList<Test>>() {}.type)
    }
}

