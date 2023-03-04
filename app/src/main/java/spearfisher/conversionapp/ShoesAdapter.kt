package spearfisher.conversionapp

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ShoesAdapter(context: Context): BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    var mShoesList= mutableListOf<Shoes>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return mShoesList.size
    }

    override fun getItem(position: Int): Any {
        return mShoesList[position]
    }

    override fun getItemId(position: Int): Long {
        return mShoesList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val shoes = mShoesList[position]

        val view: View = convertView ?: mLayoutInflater.inflate(R.layout.list_shoes,null)

        val japaneseShoeSize = view.findViewById<TextView>(R.id.japaneseShoeSizeTextView)
        japaneseShoeSize?.text = shoes.japaneseShoeSize.toString()

        val europeShoeSize = view.findViewById<TextView>(R.id.europeShoeSizeTextView)
        europeShoeSize?.text = shoes.europeShoeSize.toString()

        val usaMensShoeSize = view.findViewById<TextView>(R.id.usaMensShoeSizeTextView)
        usaMensShoeSize?.text = shoes.usaMensShoeSize.toString()

        val usaWomensShoeSize = view.findViewById<TextView>(R.id.usaWomensShoeSizeTextView)
        usaWomensShoeSize?.text = shoes.usaWomensShoeSize.toString()

        if(shoes.userShoeSizeFlag) {
            if (view != null) {
                view.setBackgroundColor(Color.rgb(0,154,217))
                japaneseShoeSize?.setTextColor(Color.WHITE)
                europeShoeSize?.setTextColor(Color.WHITE)
                usaMensShoeSize?.setTextColor(Color.WHITE)
                usaWomensShoeSize?.setTextColor(Color.WHITE)
            }
                } else {
            if (view != null) {
                view.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        return view
    }
}
