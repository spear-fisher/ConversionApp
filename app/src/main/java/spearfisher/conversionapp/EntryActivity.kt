package spearfisher.conversionapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import io.realm.Realm
import io.realm.kotlin.where
import spearfisher.conversionapp.databinding.ActivityEntryBinding
import java.util.*

class EntryActivity : AppCompatActivity(), NumberPickerDialog.NoticeDialogListener {
    private lateinit var binding: ActivityEntryBinding
    lateinit var mRealm: Realm
    private var mCalendar: Calendar? = null
    private var mShoes: Shoes? = null
    private var mPhysicalData: PhysicalData? = null
    private var userBirthYear = 1980
    private var userShoeSizeId = 0
    private var userShoeSize = 22.0
    private var bodyWeight = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRealm = Realm.getDefaultInstance()

        binding.birthYearPicker.setOnClickListener {
            val dialog = NumberPickerDialog()
            dialog.show(supportFragmentManager, "NumberPickerDialog")
        }
        val calendarRealmResults = mRealm.where(Calendar::class.java)
            .equalTo("userBirthYearFlag", true)
            .findFirst()
        if(calendarRealmResults != null){
            userBirthYear = calendarRealmResults?.westernYear
        }
        binding.birthYearPicker.hint = userBirthYear.toString()

        val spinnerItems = arrayOf(
            22.0,
            22.5,
            23.0,
            23.5,
            24.0,
            24.5,
            25.0,
            25.5,
            26.0,
            26.5,
            27.0,
            27.5,
            28.0,
            28.5,
            29.0,
            29.5,
            30.0
        )
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, spinnerItems)
        binding.shoeSizeSpinner.adapter = adapter
        val shoesRealmResults = mRealm.where(Shoes::class.java)
            .equalTo("userShoeSizeFlag", true)
            .findFirst()
        if(shoesRealmResults != null){
            userShoeSize = shoesRealmResults?.japaneseShoeSize
            userShoeSizeId = shoesRealmResults?.id
        }
        binding.shoeSizeSpinner.setSelection(userShoeSizeId)

        val physicalDataRealmResults = mRealm.where(PhysicalData::class.java).findFirst()
        if(physicalDataRealmResults != null){
            bodyWeight = physicalDataRealmResults?.bodyWeight
        }
        binding.bodyWeightEdit.hint = bodyWeight.toString()

        binding.saveButton.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
            addCalendar()
            addShoes()
            if(binding.bodyWeightEdit.getText().toString().equals("") == false) {
                addWeight(binding.bodyWeightEdit.text.toString().toDouble())
            }
        }

        binding.shoeSizeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int, id: Long
                ) {
                    val shoeSizeSpinenr = parent as Spinner
                    userShoeSize = shoeSizeSpinenr.selectedItem as Double
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun onNumberPickerDialogPositiveClick(
        dialog: DialogFragment,
        selectedItem: Int
    ) {
        binding.birthYearPicker.text = selectedItem.toString()
        userBirthYear = selectedItem
    }

    override fun onNumberPickerDialogNegativeClick(dialog: DialogFragment) {
        return
    }

    private fun addCalendar() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        mCalendar = Calendar()

        val calendarRealmResults = realm.where(Calendar::class.java).findAll()

        if(userBirthYear != 0){

            if (calendarRealmResults.max("id") != null) {
                calendarRealmResults.deleteAllFromRealm()
            }

            var i = 0
            while(i<152) {

                var identifier: Int = i

                var age = -50 + i
                var westernYear = userBirthYear -50 + i
                var japaneseEra = ""
                var japaneseYear = 0
                var userBirthYearFlag = false

                if (westernYear < 1912) {
                    japaneseEra = "明治"
                } else if (westernYear in 1912..1925) {
                    japaneseEra = "大正"
                } else if (westernYear in 1926..1988) {
                    japaneseEra = "昭和"
                } else if (westernYear in 1989..2018) {
                    japaneseEra = "平成"
                } else {
                    japaneseEra = "令和"
                }

                if (westernYear < 1912) {
                    japaneseYear = westernYear - 1867
                } else if (westernYear in 1912..1925) {
                    japaneseYear = westernYear - 1911
                } else if (westernYear in 1926..1988) {
                    japaneseYear = westernYear - 1925
                } else if (westernYear in 1989..2018) {
                    japaneseYear = westernYear - 1988
                } else {
                    japaneseYear = westernYear - 2018
                }

                if (westernYear == userBirthYear) {
                    userBirthYearFlag = true
                }

                mCalendar!!.id = identifier
                mCalendar!!.age = age
                mCalendar!!.westernYear = westernYear
                mCalendar!!.japaneseEra = japaneseEra
                mCalendar!!.japaneseYear = japaneseYear
                mCalendar!!.userBirthYearFlag = userBirthYearFlag
                realm.copyToRealmOrUpdate(mCalendar!!)

                i++
            }
        }

        realm.commitTransaction()
        realm.close()
    }

    private fun addShoes() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        mShoes = Shoes()

        val shoesRealmResults = realm.where(Shoes::class.java).findAll()
        if (shoesRealmResults.max("id") != null) {
            shoesRealmResults.deleteAllFromRealm()
        }

        var i = 0
        while(i <= 16) {

            var identifier: Int = i

            var japaneseShoeSize = 0.0
            var europeShoeSize = 0.0
            var usaMensShoeSize = 0.0
            var usaWomensShoeSize = 0.0
            var userShoeSizeFlag = false

            if (identifier <= 8) {
                japaneseShoeSize = 22 + (0.5 * i)
                europeShoeSize = 34.0 + i
                usaMensShoeSize = 4 + (0.5 * i)
                usaWomensShoeSize = 5 + (0.5 * i)
            } else {
                japaneseShoeSize = 22 + (0.5 * i)
                europeShoeSize = 42 + ((i - 8) * 0.5)
                usaMensShoeSize = 4 + (0.5 * i)
                usaWomensShoeSize = 5 + (0.5 * i)
            }

            if (japaneseShoeSize == userShoeSize) {
                userShoeSizeFlag = true
            }

            mShoes!!.id = i
            mShoes!!.japaneseShoeSize = japaneseShoeSize
            mShoes!!.europeShoeSize = europeShoeSize
            mShoes!!.usaMensShoeSize = usaMensShoeSize
            mShoes!!.usaWomensShoeSize = usaWomensShoeSize
            mShoes!!.usaWomensShoeSize = usaWomensShoeSize
            mShoes!!.userShoeSizeFlag = userShoeSizeFlag
            realm.copyToRealmOrUpdate(mShoes!!)

            i++
        }

        realm.commitTransaction()
        realm.close()
    }

    private fun addWeight (bodyWeight: Double) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        mPhysicalData = PhysicalData()

        val physicalDataRealmResults = realm.where(mPhysicalData!!::class.java).findAll()

        if(bodyWeight != null) {
            if (physicalDataRealmResults.max("id") != null) {
                physicalDataRealmResults.deleteAllFromRealm()
            }
            mPhysicalData!!.id = 0
            mPhysicalData!!.bodyWeight = bodyWeight
            realm.copyToRealmOrUpdate(mPhysicalData!!)
        }

        realm.commitTransaction()
        realm.close()
    }

}