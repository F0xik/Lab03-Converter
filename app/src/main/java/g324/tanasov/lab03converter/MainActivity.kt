package g324.tanasov.lab03converter

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import g324.tanasov.lab03converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    val dropDownDistance = arrayOf("mm", "cm", "dm", "m", "km")
    val dropDownVolume = arrayOf("mm³", "cm³", "dm³", "m³", "km³")
    val dropDownSquare = arrayOf("mm²", "cm²", "dm²", "m²", "km²")
    val dropDownSpeed = arrayOf("m/с", "m/h", "km/c", "km/h")
    var arraysType = arrayOf("mm", "cm", "dm", "m", "km")
    var numArray: Int = 1;
    var numFrom: Double = 0.0
    lateinit var strFrom: String
    lateinit var strTo: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        choiceSpin()
    }

    fun choiceSpin() {
        when (numArray) {
            1 -> arraysType = dropDownDistance
            2 -> arraysType = dropDownVolume
            3 -> arraysType = dropDownSquare
            4 -> arraysType = dropDownSpeed
            else -> {
                Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }

        val arrayAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, arraysType)
        bindingClass.spinFrom.adapter = arrayAdapter
        bindingClass.spinFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "selected unit for FROM of measurement is " + arraysType[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        bindingClass.spinTo.adapter = arrayAdapter
        bindingClass.spinTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                Toast.makeText(
                    applicationContext,
                    "selected unit for TO of measurement is " + arraysType[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

    }

    fun onClickRadioButton(view: View) {
        bindingClass.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                bindingClass.rdbDistance.id -> {
                    numArray = 1
                }

                bindingClass.rdbVolume.id -> {
                    numArray = 2
                }

                bindingClass.rdbSquare.id -> {
                    numArray = 3
                }

                bindingClass.rdbSpeed.id -> {
                    numArray = 4
                }
            }
        }
        choiceSpin()
    }

    fun Distance() {
        when (strFrom) {
            "mm" -> numFrom /= 1000.0
            "cm" -> numFrom /= 100.0
            "dm" -> numFrom /= 10.0
            "m" -> numFrom /= 1.0
            "km" -> numFrom *= 1000.0
        }
        when (strTo) {
            "mm" -> numFrom *= 1000.0
            "cm" -> numFrom *= 100.0
            "dm" -> numFrom *= 10.0
            "m" -> numFrom *= 1.0
            "km" -> numFrom /= 1000.0
        }
    }

    fun Volume() {
        when (strFrom) {
            "mm³" -> numFrom /= 1000000.0
            "cm³" -> numFrom /= 1000.0
            "dm³" -> numFrom /= 1.0
            "m³" -> {}
            "km³" -> numFrom *= 1000000000.0
        }

        when (strTo) {
            "mm³" -> numFrom *= 1000000.0
            "cm³" -> numFrom *= 1000.0
            "dm³" -> numFrom *= 1.0
            "m³" -> {}
            "km³" -> numFrom /= 1000000000.0
        }
    }

    fun Square() {
        when (strFrom) {
            "mm²" -> numFrom /= 1000000.0
            "cm²" -> numFrom /= 10000.0
            "dm²" -> numFrom /= 100.0
            "m²" -> numFrom /= 1.0
            "km²" -> numFrom *= 1000000.0
        }
        when (strTo) {
            "mm²" -> numFrom *= 1000000.0
            "cm²" -> numFrom *= 10000.0
            "dm²" -> numFrom *= 100.0
            "m²" -> numFrom *= 1.0
            "km²" -> numFrom /= 1000000.0
        }
    }

    fun Speed() {
        when (strFrom) {
            "m/с" -> numFrom /= 1.0
            "m/h" -> numFrom /= 3600.0
            "km/c" -> numFrom *= 1000.0
            "km/h" -> {
                numFrom *= 1000.0
                numFrom /= 3600.0
            }
        }

        when (strTo) {
            "m/с" -> numFrom *= 1.0
            "m/h" -> numFrom *= 3600.0
            "km/c" -> numFrom /= 1000.0
            "km/h" -> {
                numFrom *= 3600.0
                numFrom /= 1000.0
            }
        }
    }


    fun onClickConvert(view: View) {
        strFrom = bindingClass.spinFrom.selectedItem.toString()
        strTo = bindingClass.spinTo.selectedItem.toString()
        numFrom = bindingClass.etFrom.getText().toString().toDouble();
        when (numArray) {
            1 -> Distance()
            2 -> Volume()
            3 -> Square()
            4 -> Speed()
        }
        bindingClass.etTo.text.clear()
        bindingClass.etTo.setText(numFrom.toString())


    }
}
