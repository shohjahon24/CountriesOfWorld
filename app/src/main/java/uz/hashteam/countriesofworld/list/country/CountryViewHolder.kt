package uz.hashteam.countriesofworld.list.country

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.view.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.data.country.Country
import uz.hashteam.countriesofworld.util.blockClickable

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var click: ((Int) -> Unit)? = null

    fun bind(data: Country) {
        itemView.country.text = data.name
        itemView.capital.text = data.capital.name
        itemView.setOnClickListener {
            it.blockClickable()
            click?.invoke(data.id)
        }
        Glide.with(itemView.context).asBitmap()
            .load(Uri.parse("file:///android_asset/images/${data.imgName}_flag.png"))
            .error(R.drawable.belgium_flag).into(itemView.flag)
    }
}