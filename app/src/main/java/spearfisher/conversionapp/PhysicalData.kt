package spearfisher.conversionapp

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PhysicalData : RealmObject(), Serializable {
    var bodyWeight: Double = 0.0

    @PrimaryKey
    var id: Int = 0
}