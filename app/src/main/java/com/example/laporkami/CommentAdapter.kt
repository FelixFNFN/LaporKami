package com.example.laporkami

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat

class CommentAdapter(
    context: Context,
    private val listComment:ArrayList<Comment>,
    private val listNama:ArrayList<String>,
    private val loginNow:Users
) : ArrayAdapter<Comment>(context,R.layout.list_comment,listComment) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v: View? = convertView
        lateinit var holder: commentHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.list_comment, parent, false) as View
            holder = commentHolder(
                v.findViewById(R.id.tvNamauser),
                v.findViewById(R.id.tvComment),
            )
            v.setTag(holder)
        }
        else{
            holder = v.getTag() as commentHolder
        }

        val now = listComment[position]

        holder.tvNamauser.setText(listNama[position])
        holder.tvComment.setText(now.comment)



        return v
    }


}

data class commentHolder(
    val tvNamauser: TextView,
    val tvComment: TextView
)
