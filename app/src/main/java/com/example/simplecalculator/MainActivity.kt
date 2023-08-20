package com.example.simplecalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.simplecalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isLastDot = false
    private var isLastNumeric = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        bracket buttons
        binding.bracketl.setOnClickListener {
            addToInputText("(")
            makeCVisible()
            isLastDot = false
        }
        binding.bracketr.setOnClickListener {
            addToInputText(")")
            makeCVisible()
            showResult()
        }

//        digit buttons
        binding.btn0.setOnClickListener {
            addToInputText("0")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn1.setOnClickListener {
            addToInputText("1")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn2.setOnClickListener {
            addToInputText("2")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn3.setOnClickListener {
            addToInputText("3")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn4.setOnClickListener {
            addToInputText("4")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn5.setOnClickListener {
            addToInputText("5")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn6.setOnClickListener {
            addToInputText("6")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn7.setOnClickListener {
            addToInputText("7")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn8.setOnClickListener {
            addToInputText("8")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }
        binding.btn9.setOnClickListener {
            addToInputText("9")
            isLastNumeric = true
            makeCVisible()
            showResult()
        }


//        operation buttons
        binding.btnAdd.setOnClickListener {
            if (isLastNumeric) {
                addToInputText("+")
                isLastNumeric = false
                isLastDot = false
            }
        }
        binding.btnSubtract.setOnClickListener {
            if (isLastNumeric) {
                addToInputText("-")
                isLastNumeric = false
                isLastDot = false
            }
        }
        binding.btnMultiply.setOnClickListener {
            if (isLastNumeric) {
                addToInputText("*")
                isLastNumeric = false
                isLastDot = false
            }
        }
        binding.btnDivide.setOnClickListener {
            if (isLastNumeric) {
                addToInputText("/")
                isLastNumeric = false
                isLastDot = false
            }
        }
        binding.btnModulo.setOnClickListener {
            if (isLastNumeric) {
                addToInputText("%")
                isLastNumeric = false
                isLastDot = false
            }
        }
        binding.btnDot.setOnClickListener {
            if (binding.input.text.isEmpty()) {
                addToInputText("0.")
                binding.output.text = "0."
            } else if (!isLastDot && isLastNumeric) {
                addToInputText(".")
                isLastDot = true
            }
        }


        binding.btnEquals.setOnClickListener {
            showResult()
            isLastDot = false
        }


//        clearing buttons
        binding.btnBackspace.setOnClickListener {
            if (binding.input.text.isNotEmpty()) {
                try {
                    val removedLast = binding.input.text.toString().dropLast(1)
                    binding.input.text = removedLast
                    Log.d("", removedLast)
                    if (removedLast.last().isDigit() || removedLast.last() == ')') {
                        isLastNumeric = true
                        showResult()
                    }
                    if (binding.input.text.isEmpty()) {
                        makeACVisible()
                    }
                } catch (_: Exception) {

                }
            } else {
                binding.output.text = "0"
            }
        }
        binding.btnClear.setOnClickListener {
            binding.output.text = ""
            makeACVisible()
        }
        binding.btnAllclear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = "0"
            makeACVisible()
            isLastDot = false
        }

    }

    private fun addToInputText(value: String) {
        val currentValue = binding.input.text.toString() + value
        binding.input.text = currentValue
    }

    //    method to calculate result
    private fun showResult() {
        if (binding.input.text.isNotEmpty() && isLastNumeric) {
            try {
                val expression = binding.input.text.toString()
                val result = ExpressionBuilder(expression).build().evaluate()
                if (result.isNaN()) {
                    binding.output.text = getString(R.string.error)
                    binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
                } else {
                    binding.output.text = DecimalFormat("0.######").format(result).toString()
                    binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
                }
            } catch (e: Exception) {
                binding.output.text = getString(R.string.error)
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
        }
    }

    //    make cllear button visible
    private fun makeCVisible() {
        if (binding.btnAllclear.visibility == View.VISIBLE) {
            binding.btnAllclear.visibility = View.GONE
            binding.btnClear.visibility = View.VISIBLE
        }
    }

    //    make allclear button  visible
    private fun makeACVisible() {
        if (binding.btnAllclear.visibility == View.GONE) {
            binding.btnClear.visibility = View.GONE
            binding.btnAllclear.visibility = View.VISIBLE
        }
    }

}