package com.example.progmobkotlin2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmobkotlin2021.adapter.PetaniAdapter
import com.example.progmobkotlin2021.model.Petani

class SampleRecyclerView : AppCompatActivity() {
    lateinit var rvSample: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recycler_view)

        rvSample = findViewById(R.id.rvLatihan)

        //generate list petani
        val listPetani = listOf(
            Petani(user = "AW", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW2", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW3", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW4", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW5", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50")
        )

        val petaniAdapter = PetaniAdapter(listPetani)

        rvSample.apply {
            //            layoutManager = GridLayoutManager(this@MainActivity, 3)
            layoutManager = LinearLayoutManager(this@SampleRecyclerView)
            adapter = petaniAdapter
        }
    }
}