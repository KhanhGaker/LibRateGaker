package com.example.librate_gaker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import com.example.librate_gaker.databinding.DialogRateBinding
import kotlin.math.log

class DialogRate :
    BaseDialog<DialogRateBinding>(DialogRateBinding::inflate) {
    private var numberStar = 4
    private var mContext: Activity? = null
    private var appName = ""
    private var logo: Int? = null
    private var email:String? = null
    companion object {
        @JvmStatic
        fun newInstance(mContext: Activity, appName: String, logo:Int, email: String): DialogRate {
            val fragmentDialog = DialogRate()
            fragmentDialog.mContext = mContext
            fragmentDialog.appName = appName
            fragmentDialog.logo = logo
            fragmentDialog.email = email
            return fragmentDialog
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        binding.txtTile.text ="Enjoy $appName ?"
        binding.imgLogo.setImageResource(logo!!)
    }

    private fun listener() {
        binding.imgRate1s.setOnClickListener {
            numberStar = 1
            binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
            binding.imgRate2s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate3s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate4s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate5s.setImageResource(R.drawable.ic_star_un_selected)
        }
        binding.imgRate2s.setOnClickListener {
            if (numberStar == 2) {
                numberStar = 1
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_star_un_selected)
            } else {
                numberStar = 2
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
            }
            binding.imgRate3s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate4s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate5s.setImageResource(R.drawable.ic_star_un_selected)
        }
        binding.imgRate3s.setOnClickListener {
            if (numberStar == 3) {
                numberStar = 2
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate3s.setImageResource(R.drawable.ic_star_un_selected)
            } else {
                numberStar = 3
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate3s.setImageResource(R.drawable.ic_start_selected)
            }
            binding.imgRate4s.setImageResource(R.drawable.ic_star_un_selected)
            binding.imgRate5s.setImageResource(R.drawable.ic_star_un_selected)
        }
        binding.imgRate4s.setOnClickListener {
            if (numberStar == 4) {
                numberStar = 3
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate3s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate4s.setImageResource(R.drawable.ic_star_un_selected)
            } else {
                numberStar = 4
                binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate3s.setImageResource(R.drawable.ic_start_selected)
                binding.imgRate4s.setImageResource(R.drawable.ic_start_selected)
            }
            binding.imgRate5s.setImageResource(R.drawable.ic_star_un_selected)
        }
        binding.imgRate5s.setOnClickListener {
            if (numberStar == 5) {
                numberStar = 4
                binding.imgRate5s.setImageResource(R.drawable.ic_star_un_selected)
            } else {
                numberStar = 5
                binding.imgRate5s.setImageResource(R.drawable.ic_start_selected)
            }
            binding.imgRate1s.setImageResource(R.drawable.ic_start_selected)
            binding.imgRate2s.setImageResource(R.drawable.ic_start_selected)
            binding.imgRate3s.setImageResource(R.drawable.ic_start_selected)
            binding.imgRate4s.setImageResource(R.drawable.ic_start_selected)
        }

        binding.btnCloseRate.setOnClickListener {
            dismiss()
        }
        binding.btnSendRate.setOnClickListener {
            mContext?.let {
                if (numberStar <= 3) {
                    dismiss()
                    BottomSheetFeedBack(mContext!!).showBottomSheetDialog(appName,logo!!,email!!,numberStar)
                } else {
                    val shared = mContext!!.getSharedPreferences("RATE_DATA", Context.MODE_PRIVATE);
                    dismiss()
                    if (shared.getBoolean("RATE_FIRST", true)) {
                        shared.edit().putBoolean("RATE_FIRST", false).apply()
                        DirectAssessment.rateDirectAssessment(mContext!!)
                    } else {
                        val appPackageName: String = mContext!!.packageName
                        try {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "market://details?id=$appPackageName"
                                    )
                                )
                            )
                        } catch (anfe: ActivityNotFoundException) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "https://play.google.com/store/apps/details?id=$appPackageName"
                                    )
                                )
                            )
                        }
                        shared.edit().putBoolean("RATE_FIRST", true).apply()
                    }
                }
            }

        }
    }
}