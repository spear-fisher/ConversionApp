package io.github.spearfisher.conversionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.Sort
import spearfisher.conversionapp.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
    private lateinit var mAdapter: CalendarAdapter
    lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
        }

        binding.setting.setOnClickListener {
            val intent = Intent(applicationContext, EntryActivity::class.java)
            startActivity(intent)
        }

        // Realmの設定
        mRealm = Realm.getDefaultInstance()

        // ListViewの設定
        mAdapter = CalendarAdapter(this)

        // Realmデータベースから、「すべてのデータを取得してage順に並べた結果」を取得
        val calendarRealmResults = mRealm.where(Calendar::class.java).findAll().sort("age", Sort.ASCENDING)

        // 上記の結果を、CalendarListとしてセットする
        mAdapter.mCalendarList = mRealm.copyFromRealm(calendarRealmResults)

        // CalendarのListView用のアダプタに渡す
        binding.listView.adapter = mAdapter
        binding.listView.setSelection(45)

        // 表示を更新するために、アダプターにデータが変更されたことを知らせる
        mAdapter.notifyDataSetChanged()
    }
}