package uz.hashteam.countriesofworld.ui.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_content.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.data.content.Content
import uz.hashteam.countriesofworld.list.content.ContentPageAdapter
import uz.hashteam.countriesofworld.repository.Repo
import uz.hashteam.countriesofworld.ui.base.BaseFragment

class ContentFragment : BaseFragment(R.layout.fragment_content) {
    private val viewModel: ContentViewModel by activityViewModels()
    private lateinit var adapter: ContentPageAdapter
    private var fragments: ArrayList<ContentItemFragment> = ArrayList()
    override fun onCreatedView(view: View) {
        adapter = ContentPageAdapter(fragments, childFragmentManager)
        view_pager.adapter = adapter
        viewModel.getContents(Repo(requireContext()), requireArguments().getBoolean("sort"))
        setupObserves()

    }

    private fun setupObserves() {
        viewModel.data.observe(this, Observer { setupData(it) })
    }

    private fun setupData(data: List<Content>) {
        data.forEach {
            val f = ContentItemFragment()
            val b = Bundle()
            b.putString("path", it.path)
            f.arguments = b
            fragments.add(f)
        }
        adapter.notifyDataSetChanged()
        view_pager.currentItem = requireArguments().getInt("id")
    }
}