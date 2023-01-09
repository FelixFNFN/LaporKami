package com.example.laporkami

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityCommentBinding
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class CommentActivity : AppCompatActivity() {

    lateinit var loginNow:Users
    var arrLike:ArrayList<Likes> = ArrayList()
    var arrComment:ArrayList<Comment> = ArrayList()
    var arrUsers:ArrayList<String> = ArrayList()
    var idlaporan = 0
    var subjek = ""
    var detail = ""
    lateinit var tvSubjectdetail:TextView
    lateinit var tvDetaildetail:TextView
    lateinit var etComment:EditText
    lateinit var btnSendcomment:ImageButton
    lateinit var commentAdapter: CommentAdapter
    lateinit var lvComment: ListView

    val WS_HOST = "http://10.0.2.2:8000/api"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        loginNow= intent.getParcelableExtra<Users>("loginNow")!!
        idlaporan= intent.getLongExtra("id_laporan",0).toInt()
        subjek= intent.getStringExtra("subjek")!!
        detail = intent.getStringExtra("detail")!!
//        arrLike = intent.getParcelableArrayListExtra<Likes>("arrlike")!!
//        arrComment = intent.getParcelableArrayListExtra<Comment>("arrcomment")!!

        getCommentforthis()

        lvComment=findViewById(R.id.lvComment)
        commentAdapter= CommentAdapter(this,arrComment,arrUsers,loginNow)
        lvComment.adapter=commentAdapter

        tvSubjectdetail = findViewById(R.id.tvSubjectdetail)
        tvDetaildetail = findViewById(R.id.tvDetaildetail)
        etComment = findViewById(R.id.etComment)
        btnSendcomment = findViewById(R.id.btnSendcomment)

        lvComment.isFocusable=false

        tvSubjectdetail.setText("$subjek")
        tvDetaildetail.setText("$detail")


        btnSendcomment.setOnClickListener {
            if(etComment.text.toString()==""){
                Toast.makeText(this, "Comment tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                Comment()
                etComment.setText("");
                getCommentforthis()
            }
        }



    }

    fun getCommentforthis(){
        val strReq=object : StringRequest(
            Method.POST,
            "$WS_HOST/laporan/getlikeandcomment",
            Response.Listener {
                val alldata: JSONObject = JSONObject(it)

                val objlikes: JSONArray = alldata.getJSONArray("likes")
                val objcomment: JSONArray = alldata.getJSONArray("comment")

                arrComment.clear()
                arrUsers.clear()
                for (i in 0 until objcomment.length()){
                    val o=objcomment.getJSONObject(i)
                    val id=o.getString("id").toLong()
                    val id_laporan= o.getString("id_laporan").toLong()
                    val id_user= o.getString("id_user").toLong()
                    val comment= o.getString("comment")
                    val m=Comment(id,id_laporan,id_user,comment)
                    arrComment.add(m)
                    val user=o.getJSONObject("users")
                    val nama=user.getString("nama")
                    arrUsers.add(nama)
                }
                arrLike.clear()
                for (i in 0 until objlikes.length()){
                    val o=objlikes.getJSONObject(i)
                    val id=o.getString("id").toLong()
                    val id_laporan= o.getString("id_laporan").toLong()
                    val id_user= o.getString("id_user").toLong()
                    val m=Likes(id,id_laporan,id_user)
                    arrLike.add(m);
                }
                commentAdapter.notifyDataSetChanged()

            },
            Response.ErrorListener {
                Toast.makeText(this,"error getcomment", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["id_laporan"] = idlaporan.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)
    }

    fun Like(){ // fun ini dijalankan saat tombol like ditekan
        val strReq=object : StringRequest(
            Method.POST,
            "$WS_HOST/laporan/like",
            Response.Listener {
                Toast.makeText(this,"berhasil like", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["id_laporan"] = idlaporan.toString()
                params["id_user"] = loginNow.id.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)
    }

    fun Comment(){// fun ini dijalankan untuk insert comment
        val strReq=object : StringRequest(
            Method.POST,
            "$WS_HOST/laporan/insertcomment",
            Response.Listener {
                Toast.makeText(this,"berhasil comment", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["id_laporan"] = idlaporan.toString()
                params["id_user"] = loginNow.id.toString()
                params["comment"] = etComment.text.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)
    }

}