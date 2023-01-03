package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AktifitasAdapter (context: Context,
                        private val listAktifitas:List<Aktifitas>
) : ArrayAdapter<Aktifitas>(context,R.layout.list_pertanyaan,listAktifitas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: aktifitasHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_aktivitas, parent, false) as View
            holder = aktifitasHolder(
                v.findViewById(R.id.tvAktifitas),
                v.findViewById(R.id.tvNamaPenanya)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as aktifitasHolder
        }

        val now = listAktifitas[position]
        holder.tvAktifitas.setText(now.aktivitas)
        holder.tvNama.setText(now.nama)
        return v
    }
}

data class aktifitasHolder(
    val tvAktifitas: TextView,
    val tvNama: TextView
)