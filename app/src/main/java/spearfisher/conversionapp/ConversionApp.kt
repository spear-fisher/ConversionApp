package spearfisher.conversionapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class ConversionApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .build()
    }
}
