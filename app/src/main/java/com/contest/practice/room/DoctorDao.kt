package com.contest.practice.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.contest.practice.view.doctorList.Doctor
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface DoctorDao {

    @Insert
    fun insertDoctorsToDatabase(doctors: List<Doctor>): Single<List<Long>>

    @Query("SELECT * FROM doctor_table")
    fun getDoctorFromDataBase(): LiveData<List<Doctor>>

    @Query("DELETE FROM doctor_table")
    fun deleteAllDoctors(): Single<Int>
}