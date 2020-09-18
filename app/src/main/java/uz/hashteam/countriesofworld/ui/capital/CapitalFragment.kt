package uz.hashteam.countriesofworld.ui.capital

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_capital.*
import kotlinx.android.synthetic.main.fragment_capital.action_bar
import kotlinx.android.synthetic.main.fragment_capital.clear
import kotlinx.android.synthetic.main.fragment_capital.close
import kotlinx.android.synthetic.main.fragment_capital.ll_search
import kotlinx.android.synthetic.main.fragment_capital.search
import kotlinx.android.synthetic.main.fragment_capital.search_view
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.list.CallBack
import uz.hashteam.countriesofworld.list.capital.CapitalAdapter
import uz.hashteam.countriesofworld.repository.Repo
import uz.hashteam.countriesofworld.ui.base.BaseFragment
import uz.hashteam.countriesofworld.util.blockClickable

class CapitalFragment : BaseFragment(R.layout.fragment_capital), View.OnClickListener, TextWatcher,
    CallBack {

    private val viewModel: CapitalViewModel by activityViewModels()
    private lateinit var adapter: CapitalAdapter

    override fun onCreatedView(view: View) {
        search.setOnClickListener(this)
        search_view?.addTextChangedListener(this)
        clear.setOnClickListener(this)
        close.setOnClickListener(this)
        adapter = CapitalAdapter()
        adapter.capitalCallBack = this
        list_capital.adapter = adapter
        context?.let {
            viewModel.getCountries(Repo(it))
        }
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.countries.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(p0: View?) {
        p0?.blockClickable()
        when (p0?.id) {
            R.id.search -> {
                action_bar.visibility = View.INVISIBLE
                ll_search.visibility = View.VISIBLE
                showKeyboard(search_view)
            }
            R.id.clear -> {
                search_view.setText("")
                clear.visibility = View.INVISIBLE
            }
            R.id.close -> {
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
        val b = Bundle()
        hideKeyBoard()
        b.putInt("id", position)
        b.putBoolean("sort", true)
        findNavController().navigate(R.id.action_capitalFragment_to_contentFragment, b)
    }

}