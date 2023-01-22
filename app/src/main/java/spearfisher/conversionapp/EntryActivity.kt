package spearfisher.conversionapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import spearfisher.conversionapp.databinding.ActivityEntryBinding
import java.time.LocalDate
import java.util.*

class EntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEntryBinding

    private lateinit var mRealm: Realm

    private var mAge = 0
    private var mBirthYear = 0
    private var mWesternYear = 0
    private var mJapaneseEra = ""
    private var mJapaneseYear = 0
    private var mCalendar: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.birthdayButton.setOnClickListener {
            showDatePicker()
        }

        binding.startButton.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
            addCalendar()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.MyDialogTheme,
            DatePickerDialog.OnDateSetListener() {view, year, month, dayOfMonth->
                binding.birthdayButton.text = "${year}/${month + 1}/${dayOfMonth}"
                mBirthYear = year
            },
            1980,
            1,
            1)
        datePickerDialog.show()
    }

    private fun addCalendar() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        mCalendar = Calendar()

        val calendarRealmResults = realm.where(Calendar::class.java).findAll()

        if (calendarRealmResults.max("id") != null) {
            realm.deleteAll()
        }

        /*
        mCalendar!!.id = 0
        mCalendar!!.age = 0
        mCalendar!!.westernYear = 1988
        mCalendar!!.japaneseEra = "昭和"
        mCalendar!!.japaneseYear = 63
        realm.copyToRealmOrUpdate(mCalendar!!)
        realm.commitTransaction()
        */

        var i = 0
        while(i<100) {

            Log.d("DebugLog", "updateRealm x " + i)

            var identifier: Int = i

            var age = 0 + i
            var westernYear = mBirthYear + i
            var japaneseEra = ""
            var japaneseYear = 0

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

            mCalendar!!.id = identifier
            mCalendar!!.age = age
            mCalendar!!.westernYear = westernYear
            mCalendar!!.japaneseEra = japaneseEra
            mCalendar!!.japaneseYear = japaneseYear
            realm.copyToRealmOrUpdate(mCalendar!!)

            /*
            realm.executeTransaction {

                realm.copyToRealmOrUpdate(mCalendar!!)
                realm.commitTransaction()

                /*
                val id = realm.where<Calendar>().max("id")
                val nextId = (id?.toLong() ?: 0) + 1
                val obj = realm.createObject<Calendar>(nextId)
                    obj.age = age
                    obj.westernYear = westernYear
                    obj.japaneseEra = japaneseEra
                    obj.japaneseYear = japaneseYear
                */
            }
            */

            i++
        }

        Log.d("DebugLog", "insertResult:${realm.where<Calendar>().findAll()}")
        realm.commitTransaction()
        realm.close()
    }
}