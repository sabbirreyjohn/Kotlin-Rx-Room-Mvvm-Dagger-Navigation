package com.contest.practice.view.doctorList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.contest.practice.R
import com.contest.practice.databinding.FragmentDoctorsBinding


class DoctorsFragment : Fragment(R.layout.fragment_doctors),
    DoctorAdapter.OnRecycleViewItemClickListener {

    private val TAG = "DoctorsFragment"
    private val doctorViewModel: DoctorViewModel by viewModels()
    private var doctorAdapter: DoctorAdapter? = null

    private var binding: FragmentDoctorsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_doctors, container, false)
        binding = FragmentDoctorsBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.rcv?.itemAnimator = DefaultItemAnimator()
        binding?.rcv?.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        loadFromDataBase()

    }

    private fun loadFromDataBase() {
        doctorViewModel.getDoctorsFromDataBase().observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                Log.d(TAG, "onViewCreated: " + "Database empty")
                loadFromServer()
            } else {
                Log.d(TAG, "onViewCreated: " + "Database not empty")
                loadRecyclerView(it)
            }
        })
    }

    private fun loadFromServer() {
        Log.d(TAG, "loadFromServer: Loading...")
        doctorViewModel.getDoctorsFromServer().observe(viewLifecycleOwner, { list ->
            if (!list.isNullOrEmpty()) {
                Log.d(TAG, "loadFromServer: Loaded from server")
                doctorViewModel.insertDoctorsToDataBase(list).observe(viewLifecycleOwner, {
                    if (it) {
                        Log.d(TAG, "loadFromServer: Inserted to database")
                    }
                })

            } else {
                Log.d(TAG, "loadFromServer: Failed to load")

            }

        })
    }

    private fun loadRecyclerView(doctors: List<Doctor>) {
        Log.d(TAG, "loadRecyclerView: Loading...")
        doctorAdapter = DoctorAdapter(context, doctors)
        binding?.rcv?.adapter = doctorAdapter
        doctorAdapter?.setOnRecycleViewItemClickListener(this)


    }

    override fun onRecycleViewItemClick(v: View?, doctor: Doctor) {
        Log.d(TAG, "loadRecyclerView: " + doctor.name)
        var action = DoctorsFragmentDirections.actionDoctorsFragmentToDoctorDetailsFragment(doctor)
        findNavController().navigate(action)
    }
}