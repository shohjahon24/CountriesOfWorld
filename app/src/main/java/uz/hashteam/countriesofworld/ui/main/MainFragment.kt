package uz.hashteam.countriesofworld.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_end_test.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import uz.hashteam.countriesofworld.R
import uz.hashteam.countriesofworld.ui.base.BaseFragment
import uz.hashteam.countriesofworld.util.blockClickable

class MainFragment : BaseFragment(R.layout.fragment_main), View.OnClickListener {
    override fun onCreatedView(view: View) {
        country.setOnClickListener(this)
        capital.setOnClickListener(this)
        share.setOnClickListener(this)
        rate.setOnClickListener(this)
        other.setOnClickListener(this)
        test.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        p0.blockClickable()
        when (p0?.id) {
            R.id.country -> findNavController().navigate(R.id.action_mainFragment_to_countryFragment)
            R.id.capital -> findNavController().navigate(R.id.action_mainFragment_to_capitalFragment)
            R.id.test -> findNavController().navigate(R.id.action_mainFragment_to_testFragment)
            R.id.share -> share()
            R.id.rate -> rate()
        }
    }

    private fun share() {
        val link = "https://play.google.com/store/apps/details?id=com.webspektr.dunyo.mamlakatlari"
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, link)
        activity?.startActivity(
            Intent.createChooser(
                sharingIntent,
                "Yaqinlaringizga tavsiya qiling!"
            )
        )
    }

    private fun rate() {
        activity?.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.webspektr.dunyo.mamlakatlari")
            )
        )
    }

    override fun onBackPressed() {
        sureFinish()
    }

    private fun sureFinish() {
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_exit, null)
        dialog.setView(view)
        val d = dialog.create()
        view.yes.setOnClickListener {
            d.dismiss()
            activity?.let { it.finish() }
        }
        view.no.setOnClickListener {
            d.dismiss()
        }
        d.show()
    }

}