package com.contest.practice.view.doctorList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.contest.practice.R
import com.contest.practice.databinding.RowDoctorBinding


class DoctorAdapter(val context: Context?, val doctors: List<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.TheViewHolder>() {

    val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapter.TheViewHolder {

        val view = inflater.inflate(R.layout.row_doctor, parent, false)
        return TheViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorAdapter.TheViewHolder, position: Int) {
        var doctor = doctors.get(position)
        holder.bindDoctor(doctor)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

   inner class TheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var binding = RowDoctorBinding.bind(itemView)

        init {
            binding.rl.setOnClickListener { view ->

                listener?.onRecycleViewItemClick(view,doctors.get( adapterPosition))
            }
        }

        fun bindDoctor(doctor: Doctor) {
            binding.tvName.text = doctor.name
            binding.tvHospitalName.text = doctor.hospital
        }
    }

    var listener: OnRecycleViewItemClickListener? = null

    fun setOnRecycleViewItemClickListener(listener: OnRecycleViewItemClickListener?) {
        this.listener = listener
    }

    interface OnRecycleViewItemClickListener {
        fun onRecycleViewItemClick(v: View?, doctor: Doctor)
    }

}