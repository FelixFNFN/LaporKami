package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PertanyaanAdapter(
    context: Context,
    private val listLaporan:List<Laporan>
) :ArrayAdapter<Laporan>(context,R.layout.list_laporan,listLaporan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v:View? = convertView
        lateinit var holder: pertanyaanHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_laporan, parent, false) as View
            holder = pertanyaanHolder(
                v.findViewById(R.id.tvList)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as pertanyaanHolder
        }

        val now = listLaporan[position]
        holder.tvPertanyaan.setText(now.subjek)

        return v
    }
}

data class pertanyaanHolder(
    val tvPertanyaan:TextView
)