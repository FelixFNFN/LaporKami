package com.example.laporkami

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    lateinit var lvPertanyaan: ListView
    lateinit var pertanyaanAdapter: PertanyaanAdapter
    lateinit var arrPertanyaan:ArrayList<Pertanyaan>
    lateinit var arrPertanyaanDB:ArrayList<Pertanyaan>
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
        lvPertanyaan=view.findViewById(R.id.lvPertanyaan)
        arrPertanyaan= ArrayList()
        for (i in 0 .. 10){
            var newPertanyaan = Pertanyaan((i+1).toLong(),"try $i")
            arrPertanyaan.add(newPertanyaan)
        }
        pertanyaanAdapter= PertanyaanAdapter(view.context,arrPertanyaan)
        lvPertanyaan.adapter=pertanyaanAdapter
        pertanyaanAdapter.notifyDataSetChanged()
        searchingBtn.setOnClickListener {
            search(etSearch.text.toString())
            pertanyaanAdapter.notifyDataSetChanged()
        }

        tvGoCreateLaporan.setOnClickListener {
            var laporIntent=Intent(view.context,LaporActivity::class.java)
            startActivity(laporIntent)
        }
    }

    fun search(insert:String){
        for(i in 0 until arrPertanyaan.size){
            if(arrPertanyaan[i].pertanyaan.contains(insert)){

            }
        }
    }

    fun refreshList(){
        val strReq=object : StringRequest(
            Method.GET,
            "$WS_HOST/laporan",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                arrPertanyaanDB.clear()
                for (i in 0 until obj.length()){
                    val o=obj.getJSONObject(i)
                    val id=o.getString("id").toLong()
                    val pertanyaan=o.getString("pertanyaan")
                    val m=Pertanyaan(id,pertanyaan)
                    arrPertanyaanDB.add(m)
                }
                pertanyaanAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(context,"error", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)
    }
}