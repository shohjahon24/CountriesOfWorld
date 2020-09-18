package uz.hashteam.countriesofworld.ui.country

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_country.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.list.CallBack
import uz.hashteam.countriesofworld.list.country.CountryAdapter
import uz.hashteam.countriesofworld.repository.Repo
import uz.hashteam.countriesofworld.ui.base.BaseFragment
import uz.hashteam.countriesofworld.util.blockClickable

class CountryFragment : BaseFragment(R.layout.fragment_country), View.OnClickListener, TextWatcher,
    CallBack {
    private val viewModel: CountryViewModel by activityViewModels()
    private lateinit var adapter: CountryAdapter
    private var isClicked = false


    override fun onCreatedView(view: View) {
        search.setOnClickListener(this)
        search_view?.addTextChangedListener(this)
        clear.setOnClickListener(this)
        close.setOnClickListener(this)
        context?.let {
            viewModel.getCountries(Repo(it))
        }
        adapter = CountryAdapter()
        adapter.countryCallBack = this
        list_country.adapter = adapter
        if (isClicked) {
            action_bar.visibility = View.INVISIBLE
            ll_search.visibility = View.VISIBLE
            showKeyboard(search_view)
        }
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.countries.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(p0: View?) {
        p0.blockClickable()
        when (p0?.id) {
            R.id.search -> {
                isClicked = true
                action_bar.visibility = View.INVISIBLE
                ll_search.visibility = View.VISIBLE
                showKeyboard(search_view)
            }
            R.id.clear -> {
                search_view.setText("")
                clear.visibility = View.INVISIBLE
            }
            R.id.close -> {
                isClicked = false
                hideKeyBoard()
                search_view.setText("")
                action_bar.visibility = View.VISIBLE
                ll_search.visibility = View.INVISIBLE
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {
        viewModel.search("$p0")
        if (p0.toString().isNotEmpty())
            clear.visibility = View.VISIBLE
        else
            clear.visibility = View.VISIBLE
    }

    override fun onClick(position: Int) {
        // viewModel.search("")
        val b = Bundle()
        b.putInt("id", position)
        b.putBoolean("sort", false)
        isClicked = false
        hideKeyBoard()
        findNavController().navigate(R.id.action_countryFragment_to_contentFragment, b)
    }
}