package io.github.spearfisher.conversionapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import spearfisher.conversionapp.R
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

        binding.home.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.setting.setOnClickListener {
            val intent = Intent(applicationContext, EntryActivity::class.java)
            startActivity(intent)
        }

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
                    val mileView = Math.round((kilometer * 0.621371)*1000.0)/1000.0
                    val meterView = Math.round((kilometer * 1000)*100.0)/100.0
                    val yardView = Math.round((kilometer * 1093.61)*100.0)/100.0
                    val centimeterView = Math.round((kilometer * 100000)*100.0)/100.0
                    val inchView = Math.round((kilometer * 393700)*100.0)/100.0
                    binding.mileText.setText(mileView.toString())
                    binding.meterText.setText(meterView.toString())
                    binding.yardText.setText(yardView.toString())
                    binding.centimeterText.setText(centimeterView.toString())
                    binding.inchText.setText(inchView.toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (mileVal == 1) {
                    val kilometerView = Math.round((mile * 1.60934)*1000.0)/1000.0
                    val meterView = Math.round((mile * 1609.34)*100.0)/100.0
                    val yardView = Math.round((mile * 1760)*100.0)/100.0
                    val centimeterView = Math.round((mile * 160934)*100.0)/100.0
                    val inchView = Math.round((mile * 63360)*100.0)/100.0
                    binding.kilometerText.setText(kilometerView.toString())
                    binding.meterText.setText(meterView.toString())
                    binding.yardText.setText(yardView.toString())
                    binding.centimeterText.setText(centimeterView.toString())
                    binding.inchText.setText(inchView.toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (meterVal == 1) {
                    val kilometerView = Math.round((meter * 0.001)*1000.0)/1000.0
                    val mileView = Math.round((meter * 0.000621371)*1000.0)/1000.0
                    val yardView = Math.round((meter * 1.09361)*100.0)/100.0
                    val centimeterView = Math.round((meter * 100)*100.0)/100.0
                    val inchView = Math.round((meter * 39.37)*100.0)/100.0
                    binding.kilometerText.setText(kilometerView.toString())
                    binding.mileText.setText(mileView.toString())
                    binding.yardText.setText(yardView.toString())
                    binding.centimeterText.setText(centimeterView.toString())
                    binding.inchText.setText(inchView.toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (yardVal == 1) {
                    val kilometerView = Math.round((yard * 0.0009144)*1000.0)/1000.0
                    val mileView = Math.round((yard * 0.000568182)*1000.0)/1000.0
                    val meterView = Math.round((yard * 0.9144)*100.0)/100.0
                    val centimeterView = Math.round((yard * 91.44)*100.0)/100.0
                    val inchView = Math.round((yard * 36)*100.0)/100.0
                    binding.kilometerText.setText(kilometerView.toString())
                    binding.mileText.setText(mileView.toString())
                    binding.meterText.setText(meterView.toString())
                    binding.centimeterText.setText(centimeterView.toString())
                    binding.inchText.setText(inchView.toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (centimeterVal == 1) {
                    val kilometerView = Math.round((centimeter * 0.00001)*1000.0)/1000.0
                    val mileView = Math.round((centimeter * 0.00000621371)*1000.0)/1000.0
                    val meterView = Math.round((centimeter * 0.01)*100.0)/100.0
                    val yardView = Math.round((centimeter * 0.0109361)*100.0)/100.0
                    val inchView = Math.round((centimeter * 0.3937)*100.0)/100.0
                    binding.kilometerText.setText(kilometerView.toString())
                    binding.mileText.setText(mileView.toString())
                    binding.meterText.setText(meterView.toString())
                    binding.yardText.setText(yardView.toString())
                    binding.inchText.setText(inchView.toString())
                    binding.lengthButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.lengthButton.setImageResource(R.drawable.delete)
                } else if (inchVal == 1) {
                    val kilometerView = Math.round((inch * 0.000254)*100.0)/100.0
                    val mileView = Math.round((inch * 0.0001578283)*100.0)/100.0
                    val meterView = Math.round((inch * 0.0254)*100.0)/100.0
                    val yardView = Math.round((inch * 0.0277778)*100.0)/100.0
                    val centimeterView = Math.round((inch * 2.54)*100.0)/100.0
                    binding.kilometerText.setText(kilometerView.toString())
                    binding.mileText.setText(mileView.toString())
                    binding.meterText.setText(meterView.toString())
                    binding.yardText.setText(yardView.toString())
                    binding.centimeterText.setText(centimeterView.toString())
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
                    val poundView = Math.round((kilogram * 2.20462)*1000.0)/1000.0
                    val gramView = Math.round((kilogram * 1000)*100.0)/100.0
                    val ounceView = Math.round((kilogram * 35.274)*100.0)/100.0
                    binding.poundText.setText(poundView.toString())
                    binding.gramText.setText(gramView.toString())
                    binding.ounceText.setText(ounceView.toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (poundVal == 1) {
                    val kilogramView = Math.round((pound * 0.453592)*1000.0)/1000.0
                    val gramView = Math.round((pound * 453.592)*100.0)/100.0
                    val ounceView = Math.round((pound * 16)*100.0)/100.0
                    binding.kilogramText.setText(kilogramView.toString())
                    binding.gramText.setText(gramView.toString())
                    binding.ounceText.setText(ounceView.toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (gramVal == 1) {
                    val kilogramView = Math.round((gram * 0.001)*1000.0)/1000.0
                    val poundView = Math.round((gram * 0.00220462)*1000.0)/1000.0
                    val ounceView = Math.round((gram * 0.035274)*100.0)/100.0
                    binding.kilogramText.setText(kilogramView.toString())
                    binding.poundText.setText(poundView.toString())
                    binding.ounceText.setText(ounceView.toString())
                    binding.weightButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.weightButton.setImageResource(R.drawable.delete)
                } else if (ounceVal == 1) {
                    val kilogramView = Math.round((ounce * 0.0283495)*1000.0)/1000.0
                    val poundView = Math.round((ounce * 0.0625)*1000.0)/1000.0
                    val gramView = Math.round((ounce * 28.3495)*100.0)/100.0
                    binding.kilogramText.setText(kilogramView.toString())
                    binding.poundText.setText(poundView.toString())
                    binding.gramText.setText(gramView.toString())
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
                    val quartView = Math.round((liter * 1.05669)*1000.0)/1000.0
                    val milliliterView = Math.round((liter * 1000)*100.0)/100.0
                    val fluidOunceView = Math.round((liter * 33.814)*100.0)/100.0
                    binding.quartText.setText(quartView.toString())
                    binding.milliliterText.setText(milliliterView.toString())
                    binding.fluidOunceText.setText(fluidOunceView.toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (quartVal == 1) {
                    val literView = Math.round((quart * 0.946353)*1000.0)/1000.0
                    val milliliterView = Math.round((quart * 946.353)*100.0)/100.0
                    val fluidOunceView = Math.round((quart * 32)*100.0)/100.0
                    binding.literText.setText(literView.toString())
                    binding.milliliterText.setText(milliliterView.toString())
                    binding.fluidOunceText.setText(fluidOunceView.toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (milliliterVal == 1) {
                    val literView = Math.round((milliliter * 0.001)*1000.0)/1000.0
                    val quartView = Math.round((milliliter * 0.00105669)*1000.0)/1000.0
                    val fluidOunceView = Math.round((milliliter * 0.033814)*100.0)/100.0
                    binding.literText.setText(literView.toString())
                    binding.quartText.setText(quartView.toString())
                    binding.fluidOunceText.setText(fluidOunceView.toString())
                    binding.volumeButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.volumeButton.setImageResource(R.drawable.delete)
                } else if (fluidOunceVal == 1) {
                    val literView = Math.round((fluidOunce * 0.0295735)*1000.0)/1000.0
                    val quartView = Math.round((fluidOunce * 0.03125)*1000.0)/1000.0
                    val milliliterView = Math.round((fluidOunce * 0.0000295735)*100.0)/100.0
                    binding.literText.setText(literView.toString())
                    binding.quartText.setText(quartView.toString())
                    binding.milliliterText.setText(milliliterView.toString())
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
                    val fahrenheitView = Math.round(((celsius * 1.8) + 32) * 10.0) / 10.0
                    binding.fahrenheitText.setText(fahrenheitView.toString())
                    binding.temperatureButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.temperatureButton.setImageResource(R.drawable.delete)
                } else if (fahrenheitVal == 1) {
                    val celsiusView = Math.round(((fahrenheit - 32)* 0.5556)* 10.0) / 10.0
                    binding.celsiusText.setText(celsiusView.toString())
                    binding.temperatureButton.setBackgroundResource(R.drawable.image_button_bg_3)
                    binding.temperatureButton.setImageResource(R.drawable.delete)
                }
            }
        }
    }
    
    
}



