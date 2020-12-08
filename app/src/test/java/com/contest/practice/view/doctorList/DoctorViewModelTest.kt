package com.contest.practice.view.doctorList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.contest.practice.di.DaggerAppComponent
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DoctorViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val doctorViewModel = DoctorViewModel(ApplicationProvider.getApplicationContext())

    @Test
    fun testGetDoctorsFromDataBase() {
        val observer = Observer<List<Doctor>> {
        }
        var dummyList = ArrayList<Doctor>()
        var appComponent = DaggerAppComponent.create()
        dummyList.add(appComponent.getDoctor())
        dummyList.add(appComponent.getDoctor())
        dummyList.add(appComponent.getDoctor())

        Thread(Runnable {

            doctorViewModel.insertDoctorsToDataBase(dummyList)
            var getData = doctorViewModel.getDoctorsFromDataBase()
            getData.observeForever(observer)
            assertEquals(3, getData.value?.size)
            getData.removeObserver(observer)

        }).start()
    }

    @Test
    fun testGetDoctorsFromServer() {


      //  TODO("  Please change the value of BuildConfig.url_token to 7tj8JQnZTDTm4tM-ETnBg in DoctorRepository class before testing")

        val observer = Observer<List<Doctor>> {
        }
        Thread {
            var getData = doctorViewModel.getDoctorsFromServer()
            getData.observeForever(observer)
            assertEquals(5, LiveDataTestUtil.getValue(getData)?.size)
            getData.removeObserver(observer)
        }.start()


    }
}