package io.github.spearfisher.conversionapp

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Calendar : RealmObject(), Serializable {
    var age: Int = 0
    var westernYear: Int = 1988
    var japaneseEra: String = "昭和"
    var japaneseYear: Int = 63
    var userBirthYearFlag: Boolean = false

    @PrimaryKey
    var id: Int = 0
}
