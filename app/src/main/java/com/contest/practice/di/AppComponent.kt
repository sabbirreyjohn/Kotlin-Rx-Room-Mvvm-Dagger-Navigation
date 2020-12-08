package com.contest.practice.di

import com.contest.practice.view.doctorList.Doctor
import com.contest.practice.view.doctorList.DoctorViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun getDoctor(): Doctor

    fun inject(doctorViewModel: DoctorViewModel)

}