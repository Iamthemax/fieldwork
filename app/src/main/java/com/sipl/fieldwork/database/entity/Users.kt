package com.sipl.fieldwork.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var firstName: String,
    var middleName: String,
    var lastName: String,
    var mobile: String,
    var genderId: Int?=null,
    var genderName: String,
    var casteId: Int?=null,
    var casteName: String,
    var otherCasteName: String?=null,
    var villageId: Int?=null,
    var villageName: String,
    var talukaId: Int?=null,
    var talukaName: String,
    var wardNo: Int?=null,
    var dateOfBirth: String?=null,
    var address: String,
    var isSynced: Boolean?=false,
    var syncFailedReason:String? = "",
    var isSyncFailed:Boolean?=false,
    var addedByUserId:Int?=null
)