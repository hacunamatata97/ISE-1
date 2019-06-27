package com.sun.ise.data.model

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

// data class EventSuggestion(
//     private val id: Int = 0,
//     val name: String
// ) : SearchSuggestion {
//     constructor(parcel: Parcel) : this(
//         parcel.readInt()
//             parcel.readString ()!!
//     )
//
//     override fun writeToParcel(dest: Parcel?, flags: Int) {
//         dest!!.writeInt(id)
//         dest.writeString(name)
//     }
//
//     override fun describeContents(): Int = 0
//
//     override fun getBody(): String = name
//
//     companion object CREATOR : Parcelable.Creator<EventSuggestion> {
//         override fun createFromParcel(parcel: Parcel): EventSuggestion {
//             return EventSuggestion(parcel)
//         }
//
//         override fun newArray(size: Int): Array<EventSuggestion?> {
//             return arrayOfNulls(size)
//         }
//     }
// }

data class EventSuggestion(
    private val name: String
) : SearchSuggestion {

    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeInt(id)
        dest.writeString(name)
    }

    override fun describeContents(): Int = 0

    override fun getBody(): String = name

    companion object CREATOR : Parcelable.Creator<EventSuggestion> {
        override fun createFromParcel(source: Parcel): EventSuggestion {
            return EventSuggestion(source)
        }

        override fun newArray(size: Int): Array<EventSuggestion?> {
            return arrayOfNulls(size)
        }
    }
}
