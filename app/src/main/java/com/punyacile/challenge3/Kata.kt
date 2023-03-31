package com.punyacile.challenge3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.punyacile.challenge3.databinding.FragmentKataBinding

class Kata : Fragment() {
    val kataList = ArrayList<List>()
    lateinit var binding: FragmentKataBinding
    lateinit var rckata : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKataBinding.inflate(layoutInflater, container, false)
        val argumen= this.arguments
        val data = argumen?.get("Huruf")

        binding.txtStart.text = "Word That Start With $data"
        rckata = binding.rvKata
        rckata.setHasFixedSize(true)
        listKataShow()

        return binding.root
    }

    private fun getWords() : ArrayList<List> {
        val argument = this.arguments
        val data = argument?.get("Huruf")

        val kata = resources.getStringArray(R.array.kata)

        val listKata = ArrayList<List> ()
        for (i in kata.indices) {
            val kataPertama = kata[i].take(1)
            if(kataPertama == data) {
                val kata = List(kata[i])
                listKata.add(kata)
            }
        }
        return listKata
    }

    private fun listKataShow() {
        rckata.layoutManager = LinearLayoutManager(context)
        val listAdapter = AdapterKata(kataList)
        rckata.adapter = listAdapter
        kataList.clear()
        kataList.addAll(getWords())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gambar = binding.imgBack
        val kembali = Huruf()
        gambar.setOnClickListener {
            setCurrentFragment(kembali)
        }
    }

    private fun setCurrentFragment(fragment: Fragment): FragmentTransaction =
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fr_container, fragment)
            commit()
        }

}