package com.example.ca_compte.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Operation(
    val amount: String,
    val category: String,
    val date: String,
    val id: String,
    val title: String
): Parcelable