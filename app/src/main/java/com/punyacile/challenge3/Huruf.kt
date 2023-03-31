package com.punyacile.challenge3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.punyacile.challenge3.databinding.FragmentHurufBinding

class Huruf : Fragment() {
    val list = ArrayList<HurufList>()
    lateinit var binding: FragmentHurufBinding
    lateinit var rchuruf : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHurufBinding.inflate(layoutInflater, container, false)
        rchuruf = binding.rvLetter
        rchuruf.setHasFixedSize(true)
        listHurufShow()
        binding.rvLetter.setOnClickListener {

        }
        return binding.root
    }

    private fun getListHuruf() : ArrayList<HurufList> {
        val dataNama = resources.getStringArray(R.array.Abjad)
        val listAbjad = ArrayList<HurufList>()
        for (i in dataNama.indices) {
            val abjad = HurufList(dataNama[i])
            listAbjad.add(abjad)
        }
        return listAbjad
    }

    private fun listHurufShow() {
        rchuruf.layoutManager = LinearLayoutManager(context)
        var listHurufAdapter = ListAdapter(list)
        rchuruf.adapter = listHurufAdapter
        list.clear()
        list.addAll(getListHuruf())
    }

}