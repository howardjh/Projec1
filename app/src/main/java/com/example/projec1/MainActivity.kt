import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentNumber = 0.0
    private var currentOperator = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onNumberClick(view: View) {
        if (isNewOperation) {
            resultTextView.text = ""
            isNewOperation = false
        }

        val button = view as Button
        val number = button.text.toString()
        val currentText = resultTextView.text.toString()

        // Handle the special case of the 0 button
        if (number == "0" && currentText == "0") {
            return
        }

        // Append the clicked number to the current text
        resultTextView.text = currentText + number
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val operator = button.text.toString()

        if (!isNewOperation) {
            performCalculation()
        }

        currentNumber = resultTextView.text.toString().toDouble()
        currentOperator = operator
        isNewOperation = true
    }

    fun onEqualClick(view: View) {
        if (!isNewOperation) {
            performCalculation()
            isNewOperation = true
        }
    }

    fun performCalculation() {
        val newNumber = resultTextView.text.toString().toDouble()
        when (currentOperator) {
            "+" -> currentNumber += newNumber
            "-" -> currentNumber -= newNumber
            "*" -> currentNumber *= newNumber
            "/" -> currentNumber /= newNumber
            "%" -> currentNumber = (currentNumber * newNumber) / 100.0
        }
        resultTextView.text = currentNumber.toString()
    }

    fun onClearClick(view: View) {
        resultTextView.text = "0"
        currentNumber = 0.0
        currentOperator = ""
        isNewOperation = true
    }

    fun onPlusMinusClick(view: View) {
        val currentText = resultTextView.text.toString()
        if (currentText.isNotEmpty() && currentText != "0") {
            currentNumber = -currentText.toDouble()
            resultTextView.text = currentNumber.toString()
        }
    }
}
