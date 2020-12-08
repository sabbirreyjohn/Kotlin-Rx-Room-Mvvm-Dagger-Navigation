package com.contest.practice.di

import com.contest.practice.retrofit.DoctorApi
import com.contest.practice.retrofit.RetrofitInitializer
import com.contest.practice.view.doctorList.Doctor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(RetrofitInitializer.URL_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): DoctorApi {
        return retrofit.create(DoctorApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAddress(): Doctor.Address {

        return Doctor.Address("Uttara", "Dhaka", "Bangladesh", "1230")
    }

    @Singleton
    @Provides
    fun provideDoctor(address: Doctor.Address): Doctor {

        return Doctor("Sabbir Ahmed", "Medicine Specialist", "Dhaka Medical College", address)
    }
}