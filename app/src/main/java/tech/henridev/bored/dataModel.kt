package tech.henridev.bored

import com.google.gson.annotations.SerializedName

data class ActivityData(
    @SerializedName("activity")
    var title : String?,

    @SerializedName("participants")
    var people : String?,

    @SerializedName("type")
    var type : String?,

    @SerializedName("price")
    var cost : String?,

    @SerializedName("accessibility")
    var Alevel : String?
)