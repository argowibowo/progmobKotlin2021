package com.example.progmobkotlin2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmobkotlin2021.adapter.PetaniAdapter
import com.example.progmobkotlin2021.adapter.PetaniCVAdapter
import com.example.progmobkotlin2021.model.Petani

class SampleCardView : AppCompatActivity() {
    lateinit var rvCV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_card_view)

        rvCV = findViewById(R.id.rvCardView)

        //generate list petani
        val listPetani = listOf(
            Petani(user = "AW", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW2", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW3", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW4", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50"),
            Petani(user = "AW5", nama = "Argo Wibowo", jumlahLahan = "100", identifikasi = "50", tambahLahan = "50")
        )

        val petaniCVAdapter = PetaniCVAdapter(listPetani)

        rvCV.apply {
            layoutManager = LinearLayoutManager(this@SampleCardView)
            adapter = petaniCVAdapter
        }
    }
}