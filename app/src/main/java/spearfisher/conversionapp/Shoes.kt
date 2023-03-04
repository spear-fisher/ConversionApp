package spearfisher.conversionapp

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Shoes : RealmObject(), Serializable {
    var japaneseShoeSize: Double = 0.0
    var europeShoeSize: Double = 0.0
    var usaMensShoeSize: Double = 0.0
    var usaWomensShoeSize: Double = 0.0
    var userShoeSizeFlag: Boolean = false

    @PrimaryKey
    var id: Int = 0
}
