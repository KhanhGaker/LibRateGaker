package com.example.librate_gaker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.librate_gaker.databinding.BottomSheetFeedbackBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSheetFeedBack(private val activity: Activity) {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun showBottomSheetDialog(name: String, logo: Int, email: String, star: Int) {
        val binding = BottomSheetFeedbackBinding.inflate(activity.layoutInflater)
        val bottomSheerDialog = BottomSheetDialog(activity, R.style.DialogStyle)
        bottomSheerDialog.setContentView(binding.root)

        bottomSheerDialog.setOnShowListener { dialog ->
            (dialog as? BottomSheetDialog)?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                ?.let { bottomSheet ->
                    BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
                }
        }
        binding.txtNameApp.text = name

        binding.rtStar.rating = star.toFloat()
        binding.edtTell.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.txtCountCharacter.text = s.length.toString() + "/450"
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        binding.imgLogo.setImageDrawable(activity.resources.getDrawable(logo, activity.theme))
        binding.btnCancel.setOnClickListener { bottomSheerDialog.dismiss() }

        binding.btnSend.setOnClickListener(View.OnClickListener {
            if (binding.edtTell.text.toString().isEmpty()) {
                Toast.makeText(activity, "Feedback cannot be left blank", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            bottomSheerDialog.dismiss()

            val selectorIntent = Intent(Intent.ACTION_SENDTO)
            selectorIntent.setData(Uri.parse("mailto:"))
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback App $name")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "" + binding.edtTell.text.toString())
            emailIntent.selector = selectorIntent
            activity.startActivity(Intent.createChooser(emailIntent, "Send email..."))

        })
        bottomSheerDialog.show()
    }

}