package com.contest.practice.view.doctorList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.contest.practice.BuildConfig
import com.contest.practice.retrofit.DoctorApi
import com.contest.practice.retrofit.RetrofitInitializer
import com.contest.practice.room.DoctorDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DoctorRepository {


    var doctorApi: DoctorApi = RetrofitInitializer.getInstance().create(DoctorApi::class.java)

    fun getDoctorsFromServer(): MutableLiveData<List<Doctor>> {
        var ldDoctors = MutableLiveData<List<Doctor>>()
        val disposable = doctorApi.getDoctors(BuildConfig.url_token).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    Log.i("Doctor", it.toString())
                    ldDoctors.value = it.doctors
                },
                { Log.i("Doctor", "Error") })
        return ldDoctors
    }

    fun insertDoctorsToDataBase(
        doctorDao: DoctorDao,
        doctors: List<Doctor>
    ): MutableLiveData<Boolean> {
        var ldInsert = MutableLiveData<Boolean>()
        var disposable = doctorDao.insertDoctorsToDatabase(doctors).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list ->
                    ldInsert.value = list.isNotEmpty()
                }, { error ->
                    ldInsert.value = false
                })

        return ldInsert
    }

    fun deleteAllDoctorsFromDataBase(doctorDao: DoctorDao): MutableLiveData<Boolean> {
        var ldDeleted = MutableLiveData<Boolean>()
        var disposable = doctorDao.deleteAllDoctors().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                ldDeleted.value = true
            },
                {
                    ldDeleted.value = false
                })

        return ldDeleted
    }
}