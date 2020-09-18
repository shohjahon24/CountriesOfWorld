package uz.hashteam.countriesofworld.ui.content

import android.view.View
import kotlinx.android.synthetic.main.fragment_content_item.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.ui.base.BaseFragment

class ContentItemFragment : BaseFragment(R.layout.fragment_content_item) {
    override fun onCreatedView(view: View) {

        web_view.loadUrl("file:///android_asset/description_html/${requireArguments().getString("path")}")
    }
}