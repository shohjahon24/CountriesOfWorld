package uz.hashteam.countriesofworld.list.capital

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_capital.view.*
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.util.blockClickable

class CapitalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var click: ((Int) -> Unit)? = null

    fun bind(data: Country) {
        itemView.apply {
            capital.text = data.capital.name
            setOnClickListener {
                it.blockClickable()
                click?.invoke(data.capital.id)
            }
        }
    }
}