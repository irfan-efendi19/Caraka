package com.bangkit.caraka.ui.onBoarding.feature.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.caraka.databinding.OnboardingPageItemBinding
import com.bangkit.caraka.ui.onBoarding.feature.onboarding.entity.OnBoardingPage

/**
 *OnBoardingPagerAdapter adapter for the viewpager2
 *  @param onBoardingPageList as Array */
class OnBoardingPagerAdapter(private val onBoardingPageList: Array<OnBoardingPage> = OnBoardingPage.values()) :
    RecyclerView.Adapter<OnBoardingPagerAdapter.PagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PagerViewHolder =
        LayoutInflater.from(parent.context).let {
            OnboardingPageItemBinding.inflate(
                it, parent, false
            ).let { binding -> PagerViewHolder(binding) }
        }


    override fun getItemCount() = onBoardingPageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(onBoardingPageList[position])
    }

/** PagerViewHolder viewHolder inner class
 * @param binding is OnboardingPageItemBinding to bind data */
  inner  class PagerViewHolder(private val binding: OnboardingPageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    /**
     * @param onBoardingPage is OnBoardingPage item
     * bind view **/
        fun bind(onBoardingPage: OnBoardingPage) {
            val res = binding.root.context.resources
            binding.descTV.text = res.getString(onBoardingPage.descriptionResource)
            binding.img.setAnimation(onBoardingPage.logoResource)
        }

    }
}