package com.bangkit.caraka.ui.onBoarding.feature.onboarding.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.caraka.databinding.OnboardingViewBinding
import com.bangkit.caraka.ui.onBoarding.animation.setParallaxTransformation
import com.bangkit.caraka.ui.onBoarding.prefmanager.OnBoardingPrefManager
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.OnBoardingPagerAdapter
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.entity.OnBoardingPage
import com.bangkit.caraka.ui.signup.SignUpActivity

class OnBoardingView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.entries.size }
    private val prefManager: OnBoardingPrefManager


    init {
        val binding = OnboardingViewBinding.inflate(LayoutInflater.from(context), this, true)
        with(binding) {
            setUpSlider()
            addingButtonsClickListeners()
            prefManager = OnBoardingPrefManager(root.context)
        }

    }

    private fun OnboardingViewBinding.setUpSlider() {
        with(slider) {
            adapter = OnBoardingPagerAdapter()

            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }
//
//            setPageTransformer(pageCompositePageTransformer)

            addSlideChangeListener()

            val wormDotsIndicator = pageIndicator
            wormDotsIndicator.attachTo(this)
        }
    }


    private fun OnboardingViewBinding.addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun OnboardingViewBinding.addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide(slider) }
        skipBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
        startBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefManager.isFirstTimeLaunch = false
    }

    private fun navigateToNextSlide(slider: ViewPager2?) {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }


}