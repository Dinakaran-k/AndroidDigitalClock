package com.example.androiddigitalclock

import android.os.Build
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androiddigitalclock.databinding.ActivityMainBinding
import com.example.androiddigitalclock.databinding.LayoutDigitDisplayBinding
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apply {
            hourLeftDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setupLayoutWithNewDigit(binding.layoutHourLeft, map)
            }

            hourRightDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setupLayoutWithNewDigit(binding.layoutHourRight, map)
            }

            secondsLeftDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setupLayoutWithNewDigit(binding.layoutSecondsLeft, map)
            }

            secondsRightDisplayManager.digitDisplayLiveData.observe(this@MainActivity) { map ->
                setupLayoutWithNewDigit(binding.layoutSecondsRight, map)
            }

            showLiveTime()
        }


    }

    private fun setupLayoutWithNewDigit(binding: LayoutDigitDisplayBinding, map: Map<Int, Int>) {
        styleCardView(binding.segmentTop.root, map[R.id.segmentTop]!!)
        styleCardView(binding.segmentTopLeft.root, map[R.id.segmentTopLeft]!!)
        styleCardView(binding.segmentTopRight.root, map[R.id.segmentTopRight]!!)
        styleCardView(binding.segmentMiddle.root, map[R.id.segmentMiddle]!!)
        styleCardView(binding.segmentBottomLeft.root, map[R.id.segmentBottomLeft]!!)
        styleCardView(binding.segmentBottomRight.root, map[R.id.segmentBottomRight]!!)
        styleCardView(binding.segmentBottom.root, map[R.id.segmentBottom]!!)
    }

    private fun styleCardView(materialCardView: MaterialCardView, @ColorRes colorRes: Int) {
        materialCardView.apply {
            val resolvedColor = ContextCompat.getColor(context, colorRes)
            setCardBackgroundColor(resolvedColor)
        }
    }
}