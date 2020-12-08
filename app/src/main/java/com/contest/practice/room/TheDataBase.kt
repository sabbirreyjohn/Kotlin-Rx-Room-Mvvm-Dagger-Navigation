package com.contest.practice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.contest.practice.view.doctorList.Doctor

@Database(entities = [Doctor::class], version = 1, exportSchema = false)
@TypeConverters(Doctor.AddressTypeConverter::class)
abstract class TheDataBase : RoomDatabase() {

    abstract fun doctorDao(): DoctorDao

    companion object {

        private var instance: TheDataBase? = null

        @Synchronized
        fun getInstance(context: Context): TheDataBase {
            if (instance == null) {

                instance =
                    Room.databaseBuilder(context, TheDataBase::class.java, "doctor_db").build()
            }

            return instance!!
        }
    }
}