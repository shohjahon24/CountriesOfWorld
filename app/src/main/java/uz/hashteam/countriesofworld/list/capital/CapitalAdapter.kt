package uz.hashteam.countriesofworld.list.capital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.list.CallBack

class CapitalAdapter : RecyclerView.Adapter<CapitalViewHolder>() {

    private var data: List<Country> = ArrayList()

    var capitalCallBack: CallBack? = null

    fun setData(data: List<Country>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalViewHolder {
        return CapitalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_capital, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CapitalViewHolder, position: Int) {
        holder.bind(data[position])
        holder.click = {
            capitalCallBack?.onClick(it)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}