package com.contest.practice.view.doctorList

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import javax.inject.Inject

@Entity(tableName = "doctor_table")
data class Doctor @Inject constructor(
    val name: String,
    val speciality: String,
    val hospital: String,
    val address: Address,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) : Serializable {
    data class Address @Inject constructor(
        val street: String,
        val city: String,
        val state: String,
        val zip: String
    )

    class AddressTypeConverter {

        @TypeConverter
        fun addressToString(address: Address): String {
            var gson = Gson()
            val type = object : TypeToken<Address>() {}.type
            return gson.toJson(address, type)
        }

        @TypeConverter
        fun strongToAddress(string: String): Address {
            var gson = Gson()
            val type = object : TypeToken<Address>() {}.type
            return gson.fromJson(string, type)
        }
    }
}
