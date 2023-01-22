package spearfisher.conversionapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import spearfisher.conversionapp.databinding.ActivityMeasureBinding

class MeasureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeasureBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DebugLog", "openMeasureActivity")
        super.onCreate(savedInstanceState)
        binding = ActivityMeasureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val root = binding.root
        var lengthState = 0
        var weightState = 0
        var volumeState = 0
        var temperatureState = 0

        binding.lengthButton.setOnClickListener {
            // close keyboard
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            if (lengthState == 1) {
                lengthState = 0
                binding.kilometerText.setText(null)
                binding.mileText.setText(null)
                binding.meterText.setText(null)
                binding.yardText.setText(null)
                binding.centimeterText.setText(null)
                binding.inchText.setText(null)
                binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_2)
                binding.lengthButton.setImageResource(R.drawable.arrow)
            } else {
                lengthState = 1
                var kilometer = 0.0
                var mile = 0.0
                var meter = 0.0
                var yard = 0.0
                var centimeter = 0.0
                var inch = 0.0
                var kilometerVal = 1
                var mileVal = 1
                var meterVal = 1
                var yardVal = 1
                var centimeterVal = 1
                var inchVal = 1

                if (binding.kilometerText.text.isEmpty()) {
                    kilometerVal = 0
                } else {
                    kilometer = binding.kilometerText.text.toString().toDouble()
                }
                if (binding.mileText.text.isEmpty()) {
                    mileVal = 0
                } else {
                    mile = binding.mileText.text.toString().toDouble()
                }
                if (binding.meterText.text.isEmpty()) {
                    meterVal = 0
                } else {
                    meter = binding.meterText.text.toString().toDouble()
                }
                if (binding.yardText.text.isEmpty()) {
                    yardVal = 0
                } else {
                    yard = binding.yardText.text.toString().toDouble()
                }
                if (binding.centimeterText.text.isEmpty()) {
                    centimeterVal = 0
                } else {
                    centimeter = binding.centimeterText.text.toString().toDouble()
                }
                if (binding.inchText.text.isEmpty()) {
                    inchVal = 0
                } else {
                    inch = binding.inchText.text.toString().toDouble()
                }

                if (kilometerVal + mileVal + meterVal + yardVal + centimeterVal + inchVal == 0) {
                    Snackbar
                        .make(root, "数字を入力してください", Snackbar.LENGTH_LONG)
                        .show()
                } else if (kilometerVal + mileVal + meterVal + yardVal + centimeterVal + inchVal >= 2) {
                    Snackbar
                        .make(root, "一項目のみ入力してくだい", Snackbar.LENGTH_LONG)
                        .show()
                } else if (kilometerVal == 1) {
                    binding.mileText.setText((kilometer * 0.621371).toString())
                    binding.meterText.setText((kilometer * 1000).toString())
                    binding.yardText.setText((kilometer * 1093.61).toString())
                    binding.centimeterText.setText((kilometer * 100000).toString())
                    binding.inchText.setText((kilometer * 393700).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (mileVal == 1) {
                    binding.kilometerText.setText((mile * 1.60934).toString())
                    binding.meterText.setText((mile * 1609.34).toString())
                    binding.yardText.setText((mile * 1760).toString())
                    binding.centimeterText.setText((mile * 160934).toString())
                    binding.inchText.setText((mile * 63360).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (meterVal == 1) {
                    binding.kilometerText.setText((meter * 0.001).toString())
                    binding.mileText.setText((meter * 0.000621371).toString())
                    binding.yardText.setText((meter * 1.09361).toString())
                    binding.centimeterText.setText((meter * 100).toString())
                    binding.inchText.setText((meter * 39.37).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (yardVal == 1) {
                    binding.kilometerText.setText((yard * 0.0009144).toString())
                    binding.mileText.setText((yard * 0.000568182).toString())
                    binding.meterText.setText((yard * 0.9144).toString())
                    binding.centimeterText.setText((yard * 91.44).toString())
                    binding.inchText.setText((yard * 36).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (centimeterVal == 1) {
                    binding.kilometerText.setText((centimeter * 0.00001).toString())
                    binding.mileText.setText((centimeter * 0.00000621371).toString())
                    binding.meterText.setText((centimeter * 0.01).toString())
                    binding.yardText.setText((centimeter * 0.0109361).toString())
                    binding.inchText.setText((centimeter * 0.3937).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (inchVal == 1) {
                    binding.kilometerText.setText((inch * 0.000254).toString())
                    binding.mileText.setText((inch * 0.0001578283).toString())
                    binding.meterText.setText((inch * 0.0254).toString())
                    binding.yardText.setText((inch * 0.0277778).toString())
                    binding.centimeterText.setText((inch * 2.54).toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                }
            }
        }

        binding.weightButton.setOnClickListener {
            // close keyboard
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            if (weightState == 1) {
                weightState = 0
                binding.kilogramText.setText(null)
                binding.poundText.setText(null)
                binding.gramText.setText(null)
                binding.ounceText.setText(null)
                binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_2)
                binding.weightButton.setImageResource(R.drawable.arrow)
            } else {
                weightState = 1
                var kilogram = 0.0
                var pound = 0.0
                var gram = 0.0
                var ounce = 0.0
                var kilogramVal = 1
                var poundVal = 1
                var gramVal = 1
                var ounceVal = 1

                if (binding.kilogramText.text.isEmpty()) {
                    kilogramVal = 0
                } else {
                    kilogram = binding.kilogramText.text.toString().toDouble()
                }
                if (binding.poundText.text.isEmpty()) {
                    poundVal = 0
                } else {
                    pound = binding.poundText.text.toString().toDouble()
                }
                if (binding.gramText.text.isEmpty()) {
                    gramVal = 0
                } else {
                    gram = binding.gramText.text.toString().toDouble()
                }
                if (binding.ounceText.text.isEmpty()) {
                    ounceVal = 0
                } else {
                    ounce = binding.ounceText.text.toString().toDouble()
                }

                if (kilogramVal + poundVal + gramVal + ounceVal == 0) {
                    Snackbar
                        .make(root, "数字を入力してください", Snackbar.LENGTH_LONG)
                        .show()
                } else if (kilogramVal + poundVal + gramVal + ounceVal >= 2) {
                    Snackbar
                        .make(root, "一項目のみ入力してくだい", Snackbar.LENGTH_LONG)
                        .show()
                } else if (kilogramVal == 1) {
                    binding.poundText.setText((kilogram * 2.20462).toString())
                    binding.gramText.setText((kilogram * 1000).toString())
                    binding.ounceText.setText((kilogram * 35.274).toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (poundVal == 1) {
                    binding.kilogramText.setText((pound * 0.453592).toString())
                    binding.gramText.setText((pound * 453.592).toString())
                    binding.ounceText.setText((pound * 16).toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (gramVal == 1) {
                    binding.kilogramText.setText((gram * 0.001).toString())
                    binding.poundText.setText((gram * 0.00220462).toString())
                    binding.ounceText.setText((gram * 0.035274).toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (ounceVal == 1) {
                    binding.kilogramText.setText((ounce * 0.0283495).toString())
                    binding.poundText.setText((ounce * 0.0625).toString())
                    binding.gramText.setText((ounce * 28.3495).toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                }
            }
        }

        binding.volumeButton.setOnClickListener {
            // close keyboard
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            if (volumeState == 1) {
                volumeState = 0
                binding.literText.setText(null)
                binding.quartText.setText(null)
                binding.milliliterText.setText(null)
                binding.fluidOunceText.setText(null)
                binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_2)
                binding.volumeButton.setImageResource(R.drawable.arrow)
            } else {
                volumeState = 1
                var liter = 0.0
                var quart = 0.0
                var milliliter = 0.0
                var fluidOunce = 0.0
                var literVal = 1
                var quartVal = 1
                var milliliterVal = 1
                var fluidOunceVal = 1

                if (binding.literText.text.isEmpty()) {
                    literVal = 0
                } else {
                    liter = binding.literText.text.toString().toDouble()
                }
                if (binding.quartText.text.isEmpty()) {
                    quartVal = 0
                } else {
                    quart = binding.quartText.text.toString().toDouble()
                }
                if (binding.milliliterText.text.isEmpty()) {
                    milliliterVal = 0
                } else {
                    milliliter = binding.milliliterText.text.toString().toDouble()
                }
                if (binding.fluidOunceText.text.isEmpty()) {
                    fluidOunceVal = 0
                } else {
                    fluidOunce = binding.fluidOunceText.text.toString().toDouble()
                }

                if (literVal + quartVal + milliliterVal + fluidOunceVal == 0) {
                    Snackbar
                        .make(root, "数字を入力してください", Snackbar.LENGTH_LONG)
                        .show()
                } else if (literVal + quartVal + milliliterVal + fluidOunceVal >= 2) {
                    Snackbar
                        .make(root, "一項目のみ入力してくだい", Snackbar.LENGTH_LONG)
                        .show()
                } else if (literVal == 1) {
                    binding.quartText.setText((liter * 1.05669).toString())
                    binding.milliliterText.setText((liter * 1000).toString())
                    binding.fluidOunceText.setText((liter * 33.814).toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (quartVal == 1) {
                    binding.literText.setText((quart * 0.946353).toString())
                    binding.milliliterText.setText((quart * 946.353).toString())
                    binding.fluidOunceText.setText((quart * 32).toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (milliliterVal == 1) {
                    binding.literText.setText((milliliter * 0.001).toString())
                    binding.quartText.setText((milliliter * 0.00105669).toString())
                    binding.fluidOunceText.setText((milliliter * 0.033814).toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (fluidOunceVal == 1) {
                    binding.literText.setText((fluidOunce * 0.0295735).toString())
                    binding.quartText.setText((fluidOunce * 0.03125).toString())
                    binding.milliliterText.setText((fluidOunce * 0.0000295735).toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                }
            }
        }

        binding.temperatureButton.setOnClickListener {
            // close keyboard
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            if (temperatureState == 1) {
                temperatureState = 0
                binding.celsiusText.setText(null)
                binding.fahrenheitText.setText(null)
                binding.temperatureButton.setBackgroundResource(R.drawable.image_button_bg_2)
                binding.temperatureButton.setImageResource(R.drawable.arrow)
            } else {
                temperatureState = 1
                var celsius = 0.0
                var fahrenheit = 0.0
                var celsiusVal = 1
                var fahrenheitVal = 1

                if (binding.celsiusText.text.isEmpty()) {
                    celsiusVal = 0
                } else {
                    celsius = binding.celsiusText.text.toString().toDouble()
                }
                if (binding.fahrenheitText.text.isEmpty()) {
                    fahrenheitVal = 0
                } else {
                    fahrenheit = binding.fahrenheitText.text.toString().toDouble()
                }

                if (celsiusVal + fahrenheitVal == 0) {
                    Snackbar
                        .make(root, "数字を入力してください", Snackbar.LENGTH_LONG)
                        .show()
                } else if (celsiusVal + fahrenheitVal >= 2) {
                    Snackbar
                        .make(root, "一項目のみ入力してくだい", Snackbar.LENGTH_LONG)
                        .show()
                } else if (celsiusVal == 1) {
                    binding.fahrenheitText.setText(((celsius * 1.8) + 32).toString())
                    binding.temperatureButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.temperatureButton.setImageResource(R.drawable.delete)
                } else if (fahrenheitVal == 1) {
                    binding.celsiusText.setText(((fahrenheit - 32)* 0.5556).toString())
                    binding.temperatureButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.temperatureButton.setImageResource(R.drawable.delete)
                }
            }
        }
    }
    
    
}



