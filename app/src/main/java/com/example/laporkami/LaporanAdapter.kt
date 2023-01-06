package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView

class LaporanAdapter(
    context: Context,
    private val listLaporan:List<Laporan>,
    private val loginNow:Users
) :ArrayAdapter<Laporan>(context,R.layout.list_laporan,listLaporan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: laporanHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_laporan, parent, false) as View
            holder = laporanHolder(
                v.findViewById(R.id.tvList),
                v.findViewById(R.id.tvJumcom),
                v.findViewById(R.id.tvJumLike),
                v.findViewById(R.id.btnCom),
                v.findViewById(R.id.btnLike)
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as laporanHolder
        }

        val now = listLaporan[position]
        holder.tvList.setText(now.subjek)
        holder.btnLike.setOnClickListener {
            onLikeClick?.onClick(now.id.toInt())
        }
        holder.btnCom.setOnClickListener {
            onCommnetClick?.onClick(now.id.toInt())
        }



        return v
    }

    // di bawah ini digunakan saat tombol like atau tombol comment ditekan
    var onLikeClick : LikeOnClickListener? = null
    var onCommnetClick : CommentOnClickListener? = null
}

data class laporanHolder(
    val tvList: TextView,
    val tvJumCom: TextView,
    val tvJumLike: TextView,
    val btnCom: ImageButton,
    val btnLike: ImageButton
)

interface LikeOnClickListener {
    fun onClick(pos:Int)
}
interface CommentOnClickListener {
    fun onClick(pos:Int)
}