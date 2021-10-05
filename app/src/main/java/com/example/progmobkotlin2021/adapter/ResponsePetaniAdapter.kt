package com.example.progmobkotlin2021.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.progmobkotlin2021.HelpActivity
import com.example.progmobkotlin2021.R
import com.example.progmobkotlin2021.crud.AddPetaniActivity
import com.example.progmobkotlin2021.crud.UpdatePetaniActivity
import com.example.progmobkotlin2021.model.DataItem
import com.example.progmobkotlin2021.model.ResponseAddPetani
import com.example.progmobkotlin2021.model.ResponseUsersItem
import com.example.progmobkotlin2021.network.NetworkConfig
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent.getIntent
import androidx.core.content.ContextCompat
import com.example.progmobkotlin2021.crud.GetPetaniActivity


class ResponsePetaniAdapter(var petani: List<DataItem>?): RecyclerView.Adapter<ResponsePetaniAdapter.PetaniHolder>() {
    lateinit var mContext: Context
    lateinit var adapter: ResponsePetaniAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResponsePetaniAdapter.PetaniHolder {
        return PetaniHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_petani, parent, false))
    }

    override fun onBindViewHolder(holder: ResponsePetaniAdapter.PetaniHolder, position: Int) {
        holder.bindPetani(petani?.get(position))
        var popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
        popupMenu.menu.add(Menu.NONE,0,0,"Edit")
        popupMenu.menu.add(Menu.NONE,1,1,"Delete")
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val  id = menuItem.itemId
            mContext = holder.itemView.context
            if (id==0){
                //Toast.makeText(holder.itemView.context, "Edit " + petani?.get(position)?.nama.toString(), Toast.LENGTH_SHORT).show()
                var bundle = Bundle()
                var idTmp = petani?.get(position)?.id.toString()

                bundle.putString("idPetani",idTmp)
                var intent = Intent(mContext, UpdatePetaniActivity::class.java)
                intent.putExtras(bundle)
                mContext.startActivity(intent)
            }
            else if(id==1){
                //Toast.makeText(holder.itemView.context, "Hapus " + petani?.get(position)?.nama.toString(), Toast.LENGTH_SHORT).show()
                var idTmp = petani?.get(position)?.id.toString()
                NetworkConfig().getService()
                    .deletePetani(idTmp.toInt())
                    .enqueue(object : Callback<ResponseAddPetani?> {
                        override fun onFailure(call: Call<ResponseAddPetani?>, t: Throwable) {
                            Toast.makeText(holder.itemView.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                        override fun onResponse(
                            call: Call<ResponseAddPetani?>,
                            response: Response<ResponseAddPetani?>
                        ) {
                            Toast.makeText(holder.itemView.context, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()

                            (mContext as GetPetaniActivity).finish()
                            var intent = Intent(mContext, GetPetaniActivity::class.java)
                            mContext.startActivity(intent)
                        }
                    })
            }
            false
        }
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            popupMenu.show()
        })
    }

    override fun getItemCount(): Int {
        return petani?.size ?: 0
    }

    class PetaniHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtNama: TextView
        lateinit var txtJumlahLahan: TextView
        lateinit var txtAlamat: TextView
        lateinit var txtKelurahan: TextView

        fun bindPetani(petani: DataItem?) {
            itemView.apply {
                txtNama = findViewById(R.id.nama)
                txtJumlahLahan = findViewById(R.id.jumlah_lahan)
                txtAlamat = findViewById(R.id.alamat)
                txtKelurahan = findViewById(R.id.kelurahan)

                txtNama.text = petani?.nama
                txtJumlahLahan.text = petani?.jumlahLahan
                txtAlamat.text = petani?.alamat
                txtKelurahan.text = petani?.kelurahan
            }
        }
    }
}