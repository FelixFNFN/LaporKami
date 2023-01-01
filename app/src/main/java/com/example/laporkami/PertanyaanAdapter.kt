package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PertanyaanAdapter(
    context: Context,
    private val listPertanyaan:List<Pertanyaan>
) :ArrayAdapter<Pertanyaan>(context,R.layout.list_pertanyaan,listPertanyaan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v:View? = convertView
        lateinit var holder: pertanyaanHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_pertanyaan, parent, false) as View
            holder = pertanyaanHolder(
                v.findViewById(R.id.tvList)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as pertanyaanHolder
        }

        val now = listPertanyaan[position]
        holder.tvPertanyaan.setText(now.pertanyaan)

        return v
    }
}

data class pertanyaanHolder(
    val tvPertanyaan:TextView
)