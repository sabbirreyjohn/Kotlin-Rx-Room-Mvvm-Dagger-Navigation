package com.contest.practice.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface DoctorApi {
    @GET("pTUuXPKj")
    fun getDoctors(@Query("token") token: String): Single<DoctorResponse>
}