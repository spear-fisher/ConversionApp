package spearfisher.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.Sort
import spearfisher.conversionapp.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding

    private lateinit var mRealm: Realm
    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(element: Realm) {
            reloadListView()
        }
    }

    private lateinit var mAdapter: CalendarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DebugLog", "openCalendarActivity")
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        // ListView setting
        mAdapter = CalendarAdapter(this)

        reloadListView()

    }

    private fun reloadListView() {
        Log.d("DebugLog", "reloadListView")

        // Realmデータベースから、「すべてのデータを取得してage順に並べた結果」を取得
        val calendarRealmResults = mRealm.where(Calendar::class.java).findAll().sort("age", Sort.ASCENDING)

        // 上記の結果を、TaskListとしてセットする
        mAdapter.mCalendarList = mRealm.copyFromRealm(calendarRealmResults)

        // TaskのListView用のアダプタに渡す
        binding.listView.adapter = mAdapter

        // 表示を更新するために、アダプターにデータが変更されたことを知らせる
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }   

    /*
    private fun addTaskForTest() {
        val calendar = Calendar()
        calendar.age =35
        calendar.westernYear = 2023
        calendar.japaneseEra = "令和"
        calendar.japaneseYear = 5
        calendar.id = 0
        mRealm.beginTransaction()
        mRealm.copyToRealmOrUpdate(calendar)
        mRealm.commitTransaction()
    }
    */
}