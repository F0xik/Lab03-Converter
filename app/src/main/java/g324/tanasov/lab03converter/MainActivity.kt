package g324.tanasov.lab03converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import g324.tanasov.lab03converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding
    val dropDownDistanceFrom = arrayOf("mm","cm","dm","km")
    val dropDownDistanceTo = arrayOf("mm","cm","dm","km")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

}