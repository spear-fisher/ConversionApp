package spearfisher.conversionapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import spearfisher.conversionapp.databinding.ListCalendarBinding


class CalendarAdapter(context: Context) : BaseAdapter (){

    private var mLayoutInflater: LayoutInflater
    var mCalendarList = mutableListOf<Calendar>()

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
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =

        if (convertView == null) {
            val inflater = LayoutInflater.from(parent?.context)
            ListCalendarBinding.inflate(inflater, parent, false)
        } else {
            DataBindingUtil.getBinding(convertView) ?: throw IllegalStateException()
        }

        /*
        val idText = binding.idTextView as TextView
        idText.text = mCalendarList[position].id.toString()
        */

        val ageText = binding.ageTextView as TextView
        ageText.text = mCalendarList[position].age.toString()

        val westernYearText = binding.westernYearTextView as TextView
        westernYearText.text = mCalendarList[position].westernYear.toString()

        val japaneseEraText = binding.japaneseEraTextView as TextView
        japaneseEraText.text = mCalendarList[position].japaneseEra

        val japaneseYearText = binding.japaneseYearTextView as TextView
        japaneseYearText.text = mCalendarList[position].japaneseYear.toString()

        return binding?.root
    }
}