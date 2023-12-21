package com.bangkit.caraka.ui.onBoarding.feature.onboarding.entity
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.bangkit.caraka.R

enum class OnBoardingPage(
    @StringRes val descriptionResource: Int,
    @RawRes val logoResource: Int
) {
    ONE(R.string.onboarding_slide3_desc, R.raw.slide3),
    TWO(R.string.onboarding_slide4_desc, R.raw.silde4),
    THREE(R.string.onboarding_slide5_desc, R.raw.slide5),
}