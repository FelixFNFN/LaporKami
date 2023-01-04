package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AktifitasAdapter (context: Context,
                        private val listAktifitas:List<Aktifitas>
) : ArrayAdapter<Aktifitas>(context,R.layout.list_aktivitas,listAktifitas) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: aktifitasHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_aktivitas, parent, false) as View
            holder = aktifitasHolder(
                v.findViewById(R.id.tvAktifitas),
                v.findViewById(R.id.tvStatus)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as aktifitasHolder
        }

        val now = listAktifitas[position]
        var status:String
        holder.tvAktifitas.setText(now.aktivitas)
        if(now.statusCode==3){
            status="Selesai"
        }
        else if(now.statusCode==2){
            status="Diproses"
        }
        else if(now.statusCode==1){
            status="Terkirim"
        }
        else{
            status="Gagal"
        }

        holder.tvStatus.setText(status)
        return v
    }
}

data class aktifitasHolder(
    val tvAktifitas: TextView,
    val tvStatus: TextView
)