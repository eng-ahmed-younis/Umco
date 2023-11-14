package com.florinda.umcostore.screens.on_boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.indices
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.LayoutParams
import androidx.viewpager2.widget.ViewPager2
import com.florinda.umcostore.R
import com.florinda.umcostore.databinding.ActivityOnBoardingBinding
import com.florinda.umcostore.screens.main.MainActivity
import com.florinda.umcostore.screens.welcome.WelcomeActivity
import com.florinda.umcostore.utils.Constants.boards

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding :ActivityOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter
    private val viewModel by lazy { ViewModelProvider(this)[OnBoardingViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnBoardingAdapter()
        setupIndicators()
        setupCurrentIndicator(0)
        addCallbacks()
        setupObservables()
    }

     private fun addCallbacks() {
        navigateToOnBoardingPager()
        onBoardingViewsClicked()
    }

     private fun setupObservables() {
        viewModel.let { it ->
            it.fabNextOnBoardingScreen.observe(this) { if (it) nextOnBoardClicked() }
            it.backBoard.observe(this) { if (it) previousBoardClicked() }
            it.skipToNextOnBoarding.observe(this) { if (it) skipBoardingClicked() }
        }
    }


    private fun navigateToOnBoardingPager() {
        binding?.viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupCurrentIndicator(position)
            }
        })
    }

    private fun onBoardingViewsClicked() {
        binding?.apply {
            fabNext.setOnClickListener { viewModel.fabNextOnBoardingScreenClicked() }
            backBoard.setOnClickListener { viewModel.backBoardClicked() }
            skipButton.setOnClickListener { viewModel.skipToNextOnBoardingClicked() }
        }
    }

    private fun setupOnBoardingAdapter() {
        adapter = OnBoardingAdapter()
        adapter.submitList(boards.toMutableList())
        binding!!.viewPager.adapter = adapter
    }


    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(boards.size)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext) // real create indicator
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding?.indicatorsContainer?.addView(indicators[i])
        }
    }

    private fun setupCurrentIndicator(indicatorIndex: Int) {
     //   val childCount = binding?.indicatorsContainer?.childCount
        for (i in binding.indicatorsContainer.indices) {
            val imageView = binding?.indicatorsContainer?.get(i) as ImageView
            if (i == indicatorIndex)
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            else
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
        }
    }


     private fun nextOnBoardClicked() {
        viewModel.skipToNextOnBoardingClickedComplete()
        if ((binding?.viewPager?.currentItem)!! + 1 < boards.size) {
            binding!!.viewPager.currentItem += 1
        } else {
            moveToNextScreen()
        }
    }

     private fun previousBoardClicked() {
        viewModel.backBoardClickedComplete()
        if (binding!!.viewPager.currentItem == boards.indexOf(boards.first()))
            finish()
        else
            binding!!.viewPager.currentItem -= 1
    }

     private fun skipBoardingClicked() {
        viewModel.skipToNextOnBoardingClickedComplete()
        moveToNextScreen()
    }


    private fun moveToNextScreen() {
        startActivity(Intent(this , WelcomeActivity::class.java))
        finish()
    }

}