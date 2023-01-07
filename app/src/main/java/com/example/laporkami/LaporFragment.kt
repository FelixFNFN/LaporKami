package com.example.laporkami

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LaporFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaporFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var etSearch: EditText
    lateinit var searchingBtn: ImageButton
    lateinit var tvGoCreateLaporan: TextView
    lateinit var laporanAdapter: LaporanAdapter
    lateinit var lvLaporan: ListView
    var arrLaporan:ArrayList<Laporan> = ArrayList()
    var arrLike:ArrayList<Likes> = ArrayList()
    var arrComment:ArrayList<Comment> = ArrayList()
    //    var arrPertanyaanDB:ArrayList<Laporan> = ArrayList()
    lateinit var loginNow:Users
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lapor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch=view.findViewById(R.id.etSearch)
        searchingBtn=view.findViewById(R.id.searchImgBtn)
        tvGoCreateLaporan=view.findViewById(R.id.tvGoCreateLaporan)
        lvLaporan=view.findViewById(R.id.lvLaporan)
        loginNow = arguments?.getParcelable<Users>("loginNow")!!
        refreshList()
        laporanAdapter= LaporanAdapter(view.context,arrLaporan,arrLike,arrComment,loginNow)
        lvLaporan.adapter=laporanAdapter
        searchingBtn.setOnClickListener {

            laporanAdapter.notifyDataSetChanged()
        }

        laporanAdapter.onLikeClick = object:LikeOnClickListener{
            override fun onClick(pos:Int) {
                Like(pos.toString())
            }
        }

//        laporanAdapter.onCommnetClick = object:CommentOnClickListener{
//            override fun onClick(pos:Int) {
//                var detaillaporIntent=Intent(view.context,LaporActivity::class.java).apply {
//                    putExtra("loginNow",loginNow)
//                    putExtra("arrlike",arrLike)
//                    putExtra("arrcomment",arrComment)
//                }
//                startActivity(detaillaporIntent)
//            }
//        }

        tvGoCreateLaporan.setOnClickListener {
            var laporIntent=Intent(view.context,LaporActivity::class.java).apply {
                putExtra("loginNow",loginNow)
            }
            startActivity(laporIntent)
        }
    }

    fun refreshList(){
        val strReq=object : StringRequest(
            Method.GET,
            "$WS_HOST/laporan",
            Response.Listener {
                val alldata: JSONObject = JSONObject(it)

                val objlaporan: JSONArray = alldata.getJSONArray("laporan")
                val objlikes: JSONArray = alldata.getJSONArray("likes")
                val objcomment: JSONArray = alldata.getJSONArray("comment")
//                arrPertanyaanDB.clear()
                arrLaporan.clear()
                for (i in 0 until objlaporan.length()){
                    val o=objlaporan.getJSONObject(i)
                    val id=o.getString("id").toLong()
                    val laporan= o.getString("laporan")
                    val detail= o.getString("detail")
                    val id_user= o.getString("id_user").toLong()
                    val m=Laporan(id,laporan,detail,id_user)
//                    arrPertanyaanDB.add(m)
                    arrLaporan.add(m);
                }
                arrLike.clear()
                for (i in 0 until objlikes.length()){
                    val o=objlikes.getJSONObject(i)
                    val id = o.getString("id").toLong()
                    val id_laporan = o.getString("id_laporan").toLong()
                    val id_user = o.getString("id_user").toLong()
                    val m = Likes(id,id_laporan,id_user)
                    arrLike.add(m)
                }
                arrComment.clear()
                for (i in 0 until objcomment.length()){
                    val o=objcomment.getJSONObject(i)
                    val id = o.getString("id").toLong()
                    val id_laporan = o.getString("id_laporan").toLong()
                    val id_user = o.getString("id_user").toLong()
                    val comment = o.getString("comment")
                    val m = Comment(id,id_laporan,id_user,comment)
                    arrComment.add(m)
                }
                laporanAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(context,"error", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)

//        for (i in 0 until arrPertanyaanDB.size){
//            if(arrPertanyaanDB[i].pertanyaan.contains(etSearch.text.toString())){
//                arrPertanyaan.add(arrPertanyaanDB[i])
//            }
//        }
    }

    // function ini akan digunakan saat tombol like ditekan
    fun Like(idlaporan:String){
        val strReq=object : StringRequest(
            Method.POST,
            "$WS_HOST/laporan/like",
            Response.Listener {
                Toast.makeText(context,"berhasil like", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(context,"error", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params["id_laporan"] = idlaporan
                params["id_user"] = loginNow.id.toString()
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)
    }

}