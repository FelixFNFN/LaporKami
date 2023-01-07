package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat

class LaporanAdapter(
    context: Context,
    private val listLaporan:ArrayList<Laporan>,
    private val listLikes:ArrayList<Likes>,
    private val listComment:ArrayList<Comment>,
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

        var ctrLike = 0
        var ctrComment = 0
        var isLike = false
        for (i in 0 until listLikes.size){
            if(listLikes[i].id_laporan==now.id){
                ctrLike++
                if(listLikes[i].id_user==loginNow.id){
                    isLike=true
                }
            }
        }
        for (i in 0 until listComment.size){
            if(listComment[i].id_laporan==now.id){
                ctrComment++
            }
        }

        holder.tvJumLike.setText(ctrLike.toString())
        holder.tvJumCom.setText(ctrComment.toString())
        filterThisLaporan(now.id)

        if(isLike==true){
            holder.btnLike.setImageDrawable(ActivityCompat.getDrawable(context,R.drawable.ic__like))// ini belum jalan sempurna
        }
        holder.tvList.setText(now.subjek)
        holder.btnLike.setOnClickListener {
            onLikeClick?.onClick(now.id.toInt())
            if (isLike==true){
                holder.btnLike.setImageDrawable(ActivityCompat.getDrawable(context,R.drawable.ic__dislike))
                ctrLike--
                holder.tvJumLike.setText(ctrLike.toString())
                isLike=false
            }
            else{
                holder.btnLike.setImageDrawable(ActivityCompat.getDrawable(context,R.drawable.ic__like))
                ctrLike++
                holder.tvJumLike.setText(ctrLike.toString())
                isLike=true
            }
        }
        holder.btnCom.setOnClickListener {
            onCommnetClick?.onClick(now.id.toInt())
        }



        return v
    }

    // di bawah ini digunakan saat tombol like atau tombol comment ditekan
    var onLikeClick : LikeOnClickListener? = null
    var onCommnetClick : CommentOnClickListener? = null

    fun filterThisLaporan(idlaporan:Long){

    }

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
