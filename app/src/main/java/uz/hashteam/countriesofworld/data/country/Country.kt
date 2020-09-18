package uz.hashteam.countriesofworld.data.country

import uz.hashteam.countriesofworld.data.capital.Capital

data class Country(val id: Int, val capital:Capital, val name: String, val imgName: String)