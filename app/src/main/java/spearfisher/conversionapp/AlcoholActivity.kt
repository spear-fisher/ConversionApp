package spearfisher.conversionapp

import android.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.Sort
import spearfisher.conversionapp.databinding.ActivityAlcoholBinding

class AlcoholActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlcoholBinding
    lateinit var mRealm: Realm
    private var alcoholQty= 0.0
    private var alcoholPercentage = 0.0
    private var alcoholDecomposition = 0.0
    private var bodyWeight = 70.0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DebugLog", "openAlcoholActivity")
        super.onCreate(savedInstanceState)
        binding = ActivityAlcoholBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val root = binding.root

        mRealm = Realm.getDefaultInstance()
        val physicalDataRealmResults = mRealm.where(PhysicalData::class.java).findFirst()
        if(physicalDataRealmResults != null) {
            bodyWeight = physicalDataRealmResults?.bodyWeight
        }
        Log.d("DebugLog", "bodyWeight: ${bodyWeight}")
        Log.d("DebugLog", "physicalDataRealmResults: ${physicalDataRealmResults}")

        val spinnerItems = arrayOf(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, spinnerItems)
        binding.beerBottleQty.adapter = adapter
        binding.beerMugQty.adapter = adapter
        binding.wineBottleQty.adapter = adapter
        binding.wineGlassQty.adapter = adapter
        binding.sakeBottleQty.adapter = adapter
        binding.sakeTokkuriQty.adapter = adapter
        binding.liquorBottleQty.adapter = adapter
        binding.liquorGlassQty.adapter = adapter

        var beerQty = 0
        var beerBottleQty = 0
        var beerMugQty = 0
        var beerPercentage = 5
        var wineQty = 0
        var wineBottleQty = 0
        var wineGlassQty = 0
        var winePercentage = 12
        var sakeQty = 0
        var sakeBottleQty = 0
        var sakeTokkuriQty = 0
        var sakePercentage = 15
        var liquorQty = 0
        var liquorBottleQty = 0
        var liquorGlassQty = 0
        var liquorPercentage = 40
        var totalAlcohol = 0.0
        var bloodAlcohol = 0.0
        var decompositionTime = 0.0
        binding.beerPercentage.setText(beerPercentage.toString(), TextView.BufferType.EDITABLE)
        binding.winePercentage.setText(winePercentage.toString(), TextView.BufferType.EDITABLE)
        binding.sakePercentage.setText(sakePercentage.toString(), TextView.BufferType.EDITABLE)
        binding.liquorPercentage.setText(liquorPercentage.toString(), TextView.BufferType.EDITABLE)

        fun calculate(){
            totalAlcohol = ((beerQty * beerPercentage) + (wineQty * winePercentage) + (sakeQty * sakePercentage) + (liquorQty * liquorPercentage)) * 0.008
            bloodAlcohol = (totalAlcohol / (833 * bodyWeight!!)) * 100
            decompositionTime = totalAlcohol / (bodyWeight * 0.1)
            val bloodAlcoholRound = String.format("%.2f", bloodAlcohol)
            val decompositionTimeRound = String.format("%.1f", decompositionTime)
            binding.totalAlcoholOutput.setText(totalAlcohol.toString(), TextView.BufferType.NORMAL)
            binding.bloodAlcoholOutput.setText(bloodAlcoholRound, TextView.BufferType.NORMAL)
            binding.decompositionTimeOutput.setText(decompositionTimeRound, TextView.BufferType.NORMAL)
        }

        binding.home.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.setting.setOnClickListener {
            val intent = Intent(applicationContext, EntryActivity::class.java)
            startActivity(intent)
        }

        binding.beerBottleQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val beerBottleQtyParent = parent as Spinner
                val beerBottleQty = beerBottleQtyParent.selectedItem as Double
                beerQty = (beerBottleQty * 500 + beerMugQty * 350).toInt()
                if(beerQty != 0) {
                    binding.beerQty.setText(beerQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.beerMugQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val beerMugQtyParent = parent as Spinner
                val beerMugQty = beerMugQtyParent.selectedItem as Double
                beerQty = (beerBottleQty * 500 + beerMugQty * 350).toInt()
                if(beerQty != 0) {
                    binding.beerQty.setText(beerQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.beerQty.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                if(binding.beerQty.text.toString() != "") {
                    beerQty = binding.beerQty.text.toString().toInt()
                    calculate()
                }
                return@OnKeyListener true
            }
            false
        })

        binding.wineBottleQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val wineBottleQtyParent = parent as Spinner
                val wineBottleQty = wineBottleQtyParent.selectedItem as Double
                wineQty = (wineBottleQty * 750 + wineGlassQty * 125).toInt()
                if(wineQty != 0) {
                    binding.wineQty.setText(wineQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.wineGlassQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val wineGlassQtyParent = parent as Spinner
                val wineGlassQty = wineGlassQtyParent.selectedItem as Double
                wineQty = (wineBottleQty * 750 + wineGlassQty * 125).toInt()
                if(wineQty != 0) {
                    binding.wineQty.setText(wineQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.wineQty.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                if(binding.wineQty.text.toString() != "") {
                    wineQty = binding.wineQty.text.toString().toInt()
                    calculate()
                }
                return@OnKeyListener true
            }
            false
        })

        binding.sakeBottleQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val sakeBottleQtyParent = parent as Spinner
                val sakeBottleQty = sakeBottleQtyParent.selectedItem as Double
                sakeQty = (sakeBottleQty * 1800 + sakeTokkuriQty * 180).toInt()
                if(sakeQty != 0) {
                    binding.sakeQty.setText(sakeQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.sakeTokkuriQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val sakeTokkuriQtyParent = parent as Spinner
                val sakeTokkuriQty = sakeTokkuriQtyParent.selectedItem as Double
                sakeQty = (sakeBottleQty * 1800 + sakeTokkuriQty * 180).toInt()
                if(sakeQty != 0) {
                    binding.sakeQty.setText(sakeQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.sakeQty.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                if(binding.sakeQty.text.toString() != "") {
                    sakeQty = binding.sakeQty.text.toString().toInt()
                    calculate()
                }
                return@OnKeyListener true
            }
            false
        })

        binding.liquorBottleQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val liquorBottleQtyParent = parent as Spinner
                val liquorBottleQty = liquorBottleQtyParent.selectedItem as Double
                liquorQty = (liquorBottleQty * 700 + liquorGlassQty * 30).toInt()
                if(liquorQty != 0) {
                    binding.liquorQty.setText(liquorQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.liquorGlassQty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                val liquorGlassQtyParent = parent as Spinner
                val liquorGlassQty = liquorGlassQtyParent.selectedItem as Double
                liquorQty = (liquorBottleQty * 700 + liquorGlassQty * 30).toInt()
                if(liquorQty != 0) {
                    binding.liquorQty.setText(liquorQty.toString(), TextView.BufferType.NORMAL)
                }
                calculate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.liquorQty.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                if(binding.liquorQty.text.toString() != "") {
                    liquorQty = binding.liquorQty.text.toString().toInt()
                    calculate()
                }
                return@OnKeyListener true
            }
            false
        })
        
    }
}







