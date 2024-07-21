package ru.myitschool.lab23

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextA = findViewById<EditText>(R.id.side_a)
        val editTextB = findViewById<EditText>(R.id.side_b)
        val editTextC = findViewById<EditText>(R.id.side_c)

        val button = findViewById<Button>(R.id.calculate)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val textView = findViewById<TextView>(R.id.solution)

        textView.setOnClickListener {
            val valueToCopy = textView.text.toString()
            copyToClipboard(valueToCopy)
        }

        button.setOnClickListener {
            val currentSpinner = spinner.selectedItem.toString()
            val a = editTextA.text.toString().toBigDecimal()
            val b = editTextB.text.toString().toBigDecimal()
            val c = editTextC.text.toString().toBigDecimal()
            val result = when (currentSpinner) {
                "Сумма длины сторон" -> sumOfAllSidesLength(a, b, c)
                "Длина диагонали" -> diagonalLength(a, b, c)
                "Площадь полной поверхности" -> areaOfSurface(a, b, c)
                "Объем прямоугольного параллелепипеда" -> volume(a, b, c)
                else -> 0

            }
            textView.text = result.toString()
        }
    }

    private fun sumOfAllSidesLength(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal {
        val result = BigDecimal(4).multiply(a.add(b).add(c))
        return result.setScale(15, RoundingMode.HALF_UP)
    }

    private fun diagonalLength(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal {
        val squaredSum = a.pow(2).add(b.pow(2)).add(c.pow(2))
        val result = BigDecimal(sqrt(squaredSum.toDouble()))
        return result.setScale(15, RoundingMode.HALF_UP)
    }

    private fun areaOfSurface(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal {
        val result = BigDecimal(2).multiply(a.multiply(b).add(b.multiply(c)).add(a.multiply(c)))
        return result.setScale(15, RoundingMode.HALF_UP)
    }

    private fun volume(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal {
        val result = a.multiply(b).multiply(c)
        return result.setScale(15, RoundingMode.HALF_UP)
    }

    private fun copyToClipboard(value: String) {
        val clipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", value)
        clipboardManager.setPrimaryClip(clip)
    }

}

