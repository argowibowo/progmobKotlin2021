package com.example.progmobkotlin2021.crud

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmobkotlin2021.HelpActivity
import com.example.progmobkotlin2021.LoginActivity
import com.example.progmobkotlin2021.R
import com.example.progmobkotlin2021.adapter.ResponsePetaniAdapter
import com.example.progmobkotlin2021.adapter.UsersAdapter
import com.example.progmobkotlin2021.model.DataItem
import com.example.progmobkotlin2021.model.ResponsePetani
import com.example.progmobkotlin2021.model.ResponseUsersItem
import com.example.progmobkotlin2021.network.NetworkConfig
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPetaniActivity : AppCompatActivity() {
    lateinit var rvPetani : RecyclerView
    lateinit var fabAddPetani : FloatingActionButton

    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences

    override fun onRestart() {
        super.onRestart()

        rvPetani = findViewById(R.id.rvPetani)
        fabAddPetani = findViewById(R.id.fabAddPetani)

        NetworkConfig().getService()
            .getPetaniAll()
            .enqueue(object : Callback<ResponsePetani?> {
                override fun onFailure(call: Call<ResponsePetani?>, t: Throwable) {
                    Toast.makeText(this@GetPetaniActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<ResponsePetani?>,
                    response: Response<ResponsePetani?>
                ) {
                    rvPetani.apply{
                        layoutManager = LinearLayoutManager(this@GetPetaniActivity)
                        adapter = ResponsePetaniAdapter(response.body()?.data as List<DataItem>?)
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnLogout -> {
                val editor : SharedPreferences.Editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                finish()
                var intent = Intent(this@GetPetaniActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_petani)

        rvPetani = findViewById(R.id.rvPetani)
        fabAddPetani = findViewById(R.id.fabAddPetani)

        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        NetworkConfig().getService()
            .getPetaniAll()
            .enqueue(object : Callback<ResponsePetani?> {
                override fun onFailure(call: Call<ResponsePetani?>, t: Throwable) {
                    Toast.makeText(this@GetPetaniActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<ResponsePetani?>,
                    response: Response<ResponsePetani?>
                ) {
                    rvPetani.apply{
                        layoutManager = LinearLayoutManager(this@GetPetaniActivity)
                        adapter = ResponsePetaniAdapter(response.body()?.data as List<DataItem>?)
                    }
                }
            })

        fabAddPetani.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@GetPetaniActivity, AddPetaniActivity::class.java)
            startActivity(intent)
        })
    }
}