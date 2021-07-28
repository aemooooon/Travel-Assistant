/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import op.mobile.project.R
import op.mobile.project.api.QuizRecordApplication
import op.mobile.project.databinding.FragmentQuestionBinding
import op.mobile.project.model.Question
import op.mobile.project.model.QuizRecord
import op.mobile.project.repository.QuestionRepository
import op.mobile.project.utils.Constants
import op.mobile.project.viewmodel.QuizRecordViewModel
import op.mobile.project.viewmodel.QuizRecordViewModelFactory
import kotlin.collections.ArrayList

class QuestionFragment : Fragment(), View.OnClickListener {

    private lateinit var mQuizRecordViewModel: QuizRecordViewModel
    private var mCurrentPosition: Int = 1
    private var mQuestionList: List<Question>? = null
    private var mSelectOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private val handler = Handler()
    private var mCountNum = 30

    private lateinit var tvAnswerOne: TextView
    private lateinit var tvAnswerTwo: TextView
    private lateinit var tvAnswerThree: TextView
    private lateinit var tvAnswerFour: TextView
    private lateinit var ivImg: ImageView
    private lateinit var tvQuestion: TextView
    private lateinit var btnSubmit: Button
    private lateinit var tvTimer: TextView

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory =
            QuizRecordViewModelFactory((activity?.applicationContext as QuizRecordApplication).repository)
        mQuizRecordViewModel =
            ViewModelProvider(this, viewModelFactory).get(QuizRecordViewModel::class.java)

        // hide action bar
        // (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        tvAnswerOne = binding.tvAnswerOne
        tvAnswerTwo = binding.tvAnswerTwo
        tvAnswerThree = binding.tvAnswerThree
        tvAnswerFour = binding.tvAnswerFour
        ivImg = binding.ivImg
        tvQuestion = binding.tvQuestion
        btnSubmit = binding.btnSubmit
        tvTimer = binding.tvTimer

        mQuestionList = QuestionRepository(this).getQuestion()

        setQuestion()

        // added each answer click event to current context
        tvAnswerOne.setOnClickListener(this)
        tvAnswerTwo.setOnClickListener(this)
        tvAnswerThree.setOnClickListener(this)
        tvAnswerFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_answer_one -> {
                selectedOptionView(tvAnswerOne, 1)
            }
            R.id.tv_answer_two -> {
                selectedOptionView(tvAnswerTwo, 2)
            }
            R.id.tv_answer_three -> {
                selectedOptionView(tvAnswerThree, 3)
            }
            R.id.tv_answer_four -> {
                selectedOptionView(tvAnswerFour, 4)
            }
            R.id.btn_submit -> {
                removeCountDown()  // cancel countdown timer
                mCountNum = 30

                if (mSelectOptionPosition == 0) {
                    mCurrentPosition++

                    if (mCurrentPosition <= mQuestionList!!.size) {
                        setQuestion()
                    } else {
                        // construct QuizRecord object
                        val quizRecord = QuizRecord(
                            0,
                            Constants.COUNTRY_NAME,
                            mCorrectAnswers,
                            System.currentTimeMillis()
                        )
                        // insert to database
                        insertDataToDatabase(quizRecord)
                    }

                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectOptionPosition) {
                        answerView(mSelectOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // cannot change answer when submited
                    tvAnswerOne.isClickable = false
                    tvAnswerTwo.isClickable = false
                    tvAnswerThree.isClickable = false
                    tvAnswerFour.isClickable = false

                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnSubmit.text = getString(R.string.finish)
                    } else {
                        btnSubmit.text = getString(R.string.next)
                        tvTimer.text = ""
                    }

                    mSelectOptionPosition = 0
                }
            }
        }
    }

    private fun insertDataToDatabase(quizRecord: QuizRecord) {
        mQuizRecordViewModel.insertQuizRecord(quizRecord)
        Toast.makeText(activity, "Record has been saved!", Toast.LENGTH_SHORT)
            .show()
        findNavController().navigate(R.id.action_questionFragment_to_navigation_quiz)
    }

    private fun setQuestion() {
        // get question from index
        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        // check how many questions have been show up
        if (mCurrentPosition == mQuestionList!!.size) {
            btnSubmit.text = getString(R.string.finish)
        } else {
            btnSubmit.text = getString(R.string.submit)
        }

        // binding the question field to view
        "$mCurrentPosition . ${question.question}".also { tvQuestion.text = it }
        Glide.with(this).load(question.image).into(ivImg)
        tvAnswerOne.text = question.optionOne
        tvAnswerTwo.text = question.optionTwo
        tvAnswerThree.text = question.optionThree
        tvAnswerFour.text = question.optionFour

        handler.postDelayed(countDown, 0)
    }

    // default all style/background
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tvAnswerOne)
        options.add(1, tvAnswerTwo)
        options.add(2, tvAnswerThree)
        options.add(3, tvAnswerFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            if (isAdded) { // isAdded check fragment whether attach to current context
                resources.getDrawable(R.drawable.default_option_border_bg)
                    .also { option.background = it }
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        // clear default style
        defaultOptionsView()

        // added selected style
        mSelectOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        if (isAdded) {
            resources.getDrawable(R.drawable.selected_option_border_bg).also { tv.background = it }
        }
    }

    // change background when user answered
    private fun answerView(answer: Int, drawablesView: Int) {
        when (answer) {
            1 -> {
                if (isAdded) {
                    resources.getDrawable(drawablesView).also { tvAnswerOne.background = it }
                }
            }
            2 -> {
                if (isAdded) {
                    resources.getDrawable(drawablesView).also { tvAnswerTwo.background = it }
                }
            }
            3 -> {
                if (isAdded) {
                    resources.getDrawable(drawablesView).also { tvAnswerThree.background = it }
                }
            }
            4 -> {
                if (isAdded) {
                    resources.getDrawable(drawablesView).also { tvAnswerFour.background = it }
                }
            }
        }
    }

    private val countDown = object : Runnable {
        override fun run() {
            tvTimer.text = mCountNum.toString()
            if (mCountNum > 0) {
                // start count
                handler.postDelayed(this, 1000)
                tvTimer.setTextColor(Color.parseColor("#ff99cc00"))

                // set answer text view clickable when normal scenario
                tvAnswerOne.isClickable = true
                tvAnswerTwo.isClickable = true
                tvAnswerThree.isClickable = true
                tvAnswerFour.isClickable = true
            } else {
                // disable answer when time out
                tvAnswerOne.isClickable = false
                tvAnswerTwo.isClickable = false
                tvAnswerThree.isClickable = false
                tvAnswerFour.isClickable = false

                // when time out change text color and button text
                if (isAdded) {
                    btnSubmit.text = context?.getString(R.string.next)
                    tvTimer.setTextColor(Color.parseColor("#ffff4444"))
                    tvTimer.text = getString(R.string.timeout)
                }
            }
            mCountNum--
        }
    }

    private fun removeCountDown() {
        handler.removeCallbacks(countDown)
    }

}