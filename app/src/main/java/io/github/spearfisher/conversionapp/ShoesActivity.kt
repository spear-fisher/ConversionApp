package io.github.spearfisher.conversionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.Sort
import spearfisher.conversionapp.databinding.ActivityShoesBinding

class ShoesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoesBinding
    private lateinit var mAdapter: ShoesAdapter
    lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityShoesBinding.inflate(layoutInflater)
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
        mAdapter = ShoesAdapter(this)

        // Realmデータベースから、「すべてのデータを取得してage順に並べた結果」を取得
        val shoesRealmResults = mRealm.where(Shoes::class.java).findAll().sort("id", Sort.ASCENDING)

        // 上記の結果を、ShoesListとしてセットする
        mAdapter.mShoesList = mRealm.copyFromRealm(shoesRealmResults)

        // ShoesのListView用のアダプタに渡す
        binding.listView.adapter = mAdapter

        // 表示を更新するために、アダプターにデータが変更されたことを知らせる
        mAdapter.notifyDataSetChanged()
    }
}