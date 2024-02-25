package com.example.androidsaveviewapplication

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Store(var name:String, var email: String?, var phone: String?, var address:String): Serializable {}
