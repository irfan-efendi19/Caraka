package com.bangkit.caraka.ui.onBoarding.feature.onboarding.entity
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.bangkit.caraka.R

enum class OnBoardingPage(
    @StringRes val descriptionResource: Int,
    @RawRes val logoResource: Int
) {
    ONE(R.string.onboarding_slide1_desc, R.raw.test),
    TWO(R.string.onboarding_slide2_desc, R.raw.slide2),
    THREE(R.string.onboarding_slide3_desc, R.raw.slide3),
    FOUR(R.string.onboarding_slide4_desc, R.raw.silde4),
    FIVE(R.string.onboarding_slide5_desc, R.raw.slide5)
}