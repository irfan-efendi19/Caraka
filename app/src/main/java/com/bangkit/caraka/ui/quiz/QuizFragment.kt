package com.bangkit.caraka.ui.quiz

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bangkit.caraka.R
import com.bangkit.caraka.data.database.Question
import com.bangkit.caraka.data.dummydata.DummyDataAksara
import com.bangkit.caraka.databinding.FragmentQuizBinding
import com.bangkit.caraka.utill.showToast


class QuizFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuizBinding? = null
    private lateinit var binding: FragmentQuizBinding
    private lateinit var mQuestionsList: ArrayList<Question>

    private var mSelectedPosition: Int = 0
    private var mCorrectAnswer: Int = 0
    private var mCurrentPosition: Int = 1
    private var submitCounter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        mQuestionsList = DummyDataAksara.getQuestionQuestionBali()

        Log.i("info", mCurrentPosition.toString())

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        setQuestion()

        return binding.root
    }

    private fun setQuestion() {
        val question: Question = mQuestionsList[mCurrentPosition - 1]

        //re enable options
        selectedOptionView(binding.tvOptionOne,true)
        selectedOptionView(binding.tvOptionTwo,true)
        selectedOptionView(binding.tvOptionThree,true)
        selectedOptionView(binding.tvOptionFour,true)

        binding.tvQuestion.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = mCurrentPosition
        // menampilkan jumblah pertanyaan
        binding.tvProgress.text =
            "$mCurrentPosition" + "/" + binding.pb.max


        // set default saat sudah selesai
        defaultAppearance()

        // semua pertanyan terpenuhi
        if (mCurrentPosition != mQuestionsList.size && submitCounter == 0) { binding.btnSubmit.text = "Jawab" }
    }

    private fun defaultAppearance() {
        //kontrol textview
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background =
                context?.let { ContextCompat.getDrawable(it, R.drawable.defaultselect_option) }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optionOne -> {
                selectedOptionView(binding.tvOptionOne, 1)
                mSelectedPosition = 1
            }

            R.id.tv_optionTwo -> {
                selectedOptionView(binding.tvOptionTwo, 2)
                mSelectedPosition = 2
            }

            R.id.tv_optionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
                mSelectedPosition = 3
            }

            R.id.tv_optionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
                mSelectedPosition = 4
            }


            R.id.btnSubmit -> {
                if (mSelectedPosition == 0) {
                    showToast(requireContext(), "Pilih jawaban terlebih dahulu")
                    return
                } else {
                    submitCounter++
                }

                // User submits an answer
                val question = mQuestionsList[mCurrentPosition - 1]
                if (question.correctAnswer != mSelectedPosition) {
                    answerView(mSelectedPosition, R.drawable.wrong_border)
                } else {
                    mCorrectAnswer++
                }
                answerView(question.correctAnswer, R.drawable.correct_border)

                // Disable options
                selectedOptionView(binding.tvOptionOne, false)
                selectedOptionView(binding.tvOptionTwo, false)
                selectedOptionView(binding.tvOptionThree, false)
                selectedOptionView(binding.tvOptionFour, false)

                // Update button text based on the current position
                if (mCurrentPosition < mQuestionsList.size ) {
                    binding.btnSubmit.text = "Pertanyaan Selanjutnya"
                } else {
                    binding.btnSubmit.text = "Selesai"
                }

                // Check if it's time to move to the next question
                if (submitCounter == 2) {
                    mSelectedPosition = 0
                    mCurrentPosition++
                    submitCounter = 0

                    if (mCurrentPosition <= mQuestionsList.size) {
                        setQuestion()
                    } else {
                        // Display the final score
                        val action = QuizFragmentDirections.actionGameFragmentToScoreFragment()
                        val nameOfPlayer by navArgs<QuizFragmentArgs>()
                        action.score = mCorrectAnswer
                        findNavController().navigate(action)
                    }
                }
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedPosition: Int) {
        //reset textview
        defaultAppearance()

        mSelectedPosition = selectedPosition

        tv.setTextColor(Color.parseColor("#363A43"))
        //default tampilan
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            context?.let { ContextCompat.getDrawable(it, R.drawable.select_border) }
    }

    private fun answerView(mSelectedPosition: Int, drawableView: Int) {
        when (mSelectedPosition) {
            1 -> {
                binding.tvOptionOne.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            2 -> {
                binding.tvOptionTwo.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            3 -> {
                binding.tvOptionThree.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            4 -> {
                binding.tvOptionFour.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectedOptionView(tv: TextView, enableOptions: Boolean) {
        tv.isEnabled = enableOptions
    }
}