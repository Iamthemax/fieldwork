package com.sipl.fieldwork.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("caste")
data class Caste(
    @PrimaryKey(autoGenerate = true) var xid: Int? = null,
    val created_at: String,
    val gender_name: String,
    val id: Int,
    val is_active: Int,
    val updated_at: String
)