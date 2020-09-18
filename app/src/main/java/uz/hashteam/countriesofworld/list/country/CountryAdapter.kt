package uz.hashteam.countriesofworld.list.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.list.CallBack

class CountryAdapter : RecyclerView.Adapter<CountryViewHolder>() {
    private var data: List<Country> = ArrayList()

    var countryCallBack: CallBack? = null
    fun setData(data: List<Country>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(data[position])
        holder.click = {
            countryCallBack?.onClick(it)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}