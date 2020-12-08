package com.contest.practice.view.doctorDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.contest.practice.R
import com.contest.practice.databinding.FragmentDoctorDetailsBinding


class DoctorDetailsFragment : Fragment() {

    private val selectedDoctor: DoctorDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bindingUtil = DataBindingUtil.inflate<FragmentDoctorDetailsBinding>(
            inflater,
            R.layout.fragment_doctor_details,
            container,
            false
        )
        bindingUtil.doctor = selectedDoctor.selectedDoctor
        return bindingUtil.root
    }
}