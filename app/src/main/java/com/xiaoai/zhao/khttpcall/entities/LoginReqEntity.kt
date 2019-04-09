package com.xiaoai.zhao.khttpcall.entities

import android.os.Parcel
import android.os.Parcelable
import com.xiaoai.zhao.khttpcall.base.base_entities.BaseRequestEntity


class LoginReqEntity() : BaseRequestEntity(), Parcelable {
    lateinit var username:String
    lateinit var password:String

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        password = parcel.readString()
    }


    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginReqEntity> {
        override fun createFromParcel(parcel: Parcel): LoginReqEntity {
            return LoginReqEntity(parcel)
        }

        override fun newArray(size: Int): Array<LoginReqEntity?> {
            return arrayOfNulls(size)
        }
    }

}
