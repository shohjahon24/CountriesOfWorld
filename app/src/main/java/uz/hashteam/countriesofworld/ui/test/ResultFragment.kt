package uz.hashteam.countriesofworld.ui.test

import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.fragment_result.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.ui.base.BaseFragment

class ResultFragment : BaseFragment(R.layout.fragment_result) {
    override fun onCreatedView(view: View) {
        val r = requireArguments().getInt("result")
        result.text = "$r/20"
        emotion.setImageResource(
            when {
                r < 6 -> R.drawable.emotion0
                r < 11 -> R.drawable.emotion1
                r < 16 -> R.drawable.emotion2
                r < 19 -> R.drawable.emotion3
                else -> R.drawable.emotion4
            }
        )
    }
}