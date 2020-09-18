package uz.hashteam.countriesofworld.ui.test

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.dialog_end_test.view.*
import kotlinx.android.synthetic.main.fragment_test.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.repository.Repo
import uz.hashteam.countriesofworld.ui.base.BaseFragment
import uz.hashteam.countriesofworld.util.blockClickable

class TestFragment : BaseFragment(R.layout.fragment_test), View.OnClickListener {

    private val viewModel: TestViewModel by activityViewModels()

    private var pos = 0

    override fun onCreatedView(view: View) {
        answer1.setOnClickListener(this)
        answer2.setOnClickListener(this)
        answer3.setOnClickListener(this)
        answer4.setOnClickListener(this)
        viewModel.getTests(Repo(requireContext()))
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.test.observe(this, Observer {
            number.text = "Q ${pos + 1}/20"
            question.text = it.question
            answer1.text = it.choice[0]
            answer2.text = it.choice[1]
            answer3.text = it.choice[2]
            answer4.text = it.choice[3]
        })
        viewModel.count.observe(this, Observer {
            if (pos == 20) {
                val b = Bundle()
                b.putInt("result", it)
                activity?.let { a ->
                    Navigation.findNavController(a, R.id.container).navigate(
                        R.id.action_testFragment_to_resultFragment,
                        b
                    )
                }
                pos = 0
            }
        })
    }

    override fun onClick(p0: View?) {
        p0.blockClickable()
        pos++
        viewModel.goNext(pos, (p0 as Button).text.toString())
    }

    override fun onBackPressed() {
        sureFinish()
    }

    private fun sureFinish() {
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_end_test, null)
        dialog.setView(view)
        val d = dialog.create()
        view.yes.setOnClickListener {
            d.dismiss()
            finish()
        }
        view.no.setOnClickListener {
            d.dismiss()
        }
        d.show()
    }


}