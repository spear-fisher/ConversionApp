package io.github.spearfisher.conversionapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import spearfisher.conversionapp.R

class CalendarAdapter(context: Context): BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    var mCalendarList= mutableListOf<Calendar>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return mCalendarList.size
    }

    override fun getItem(position: Int): Any {
        return mCalendarList[position]
    }

    override fun getItemId(position: Int): Long {
        return mCalendarList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val calendar = mCalendarList[position]

        val view: View = convertView ?: mLayoutInflater.inflate(R.layout.list_calendar,null)

        val age = view.findViewById<TextView>(R.id.ageTextView)
        age?.text = calendar.age.toString()

        val westernYear = view.findViewById<TextView>(R.id.westernYearTextView)
        westernYear?.text = calendar.westernYear.toString()

        val japaneseEra = view.findViewById<TextView>(R.id.japaneseEraTextView)
        japaneseEra?.text = calendar.japaneseEra.toString()

        val japaneseYear = view.findViewById<TextView>(R.id.japaneseYearTextView)
        japaneseYear?.text = calendar.japaneseYear.toString()

        if(calendar.userBirthYearFlag) {
            if (view != null) {
                view.setBackgroundColor(Color.rgb(0,154,217))
                age?.setTextColor(Color.WHITE)
                westernYear?.setTextColor(Color.WHITE)
                japaneseEra?.setTextColor(Color.WHITE)
                japaneseYear?.setTextColor(Color.WHITE)
            }
        } else {
            if (view != null) {
                view.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        return view
    }
}
