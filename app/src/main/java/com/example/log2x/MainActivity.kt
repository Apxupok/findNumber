package com.example.log2x


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var editTextFirstNumber: EditText
    private lateinit var editTextSecondNumber: EditText
    private lateinit var currentNumberView: TextView
    private lateinit var countAttemptView: TextView
    var number_smallest = 0
    var number_bigest = 0
    var currentNumber = 0
    var lastNumber = 0
    var countAttmepts = 0





    // a method to toast a message given
    fun toastMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }



    // textWatcher is for watching any changes in editText
    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // this function is called before text is edited
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // this function is called when text is edited


        }

        @SuppressLint("SetTextI18n")
        override fun afterTextChanged(s: Editable) {
            // this function is called after text is edited

            if (editTextFirstNumber.text.toString().isNotEmpty() && editTextSecondNumber.text.toString().isNotEmpty() &&editTextSecondNumber.text.toString().toInt()!=0){
                number_smallest = editTextFirstNumber.text.toString().toInt()
                number_bigest = editTextSecondNumber.text.toString().toInt()
                countAttmepts = log(number_bigest.toDouble() - number_smallest.toDouble(), 2.0).toInt()

                currentNumber = (number_bigest-number_smallest)/2
                currentNumberView.text = currentNumber.toString()
                countAttemptView.text = countAttmepts.toString()
            }


        }

    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentNumberView = findViewById(R.id.currentNumberView)
        countAttemptView = findViewById(R.id.countAttemptView)
        toastMsg("Приложение запущено")

        // register UI  element
        // with their ID
        editTextFirstNumber = findViewById(R.id.editTextFirstNumber)
        editTextSecondNumber = findViewById(R.id.editTextSecondNumber)


        // set the TextChange Listener for
        // the edit text field
        editTextFirstNumber.addTextChangedListener(textWatcher)
        editTextSecondNumber.addTextChangedListener(textWatcher)

        val button_less: Button = findViewById(R.id.button_less)
        val button_more: Button = findViewById(R.id.button_more)

        button_less.setOnClickListener {
            number_bigest = currentNumber
            currentNumber = (number_bigest + number_smallest)/2
            currentNumberView.text = currentNumber.toString()
            countAttmepts-=1
            countAttemptView.text = countAttmepts.toString()
        }
        button_more.setOnClickListener {
            number_smallest = currentNumber
            currentNumber = (number_bigest + number_smallest)/2
            currentNumberView.text = (currentNumber).toString()
            countAttmepts-=1
            countAttemptView.text = countAttmepts.toString()
        }

    }

}