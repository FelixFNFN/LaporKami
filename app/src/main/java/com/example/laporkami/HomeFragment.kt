package com.example.laporkami

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityHomeBinding
import com.example.laporkami.databinding.FragmentHomeBinding
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var etNIK:EditText
    lateinit var btnCari:Button
    lateinit var lvNotif:ListView
    lateinit var aktifitasAdapter: AktifitasAdapter
    lateinit var arrAktivitas:ArrayList<Aktifitas>
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    var onItemClick:((selectedAktifitas:Aktifitas)->Unit)?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCari=view.findViewById(R.id.btnCari)
        etNIK=view.findViewById(R.id.etNIK)
        lvNotif=view.findViewById(R.id.listNotif)
        arrAktivitas=ArrayList()
        aktifitasAdapter= AktifitasAdapter(view.context,arrAktivitas)
        aktifitasAdapter.onItemClick={
            onItemClick?.invoke(it)
        }
        lvNotif.adapter=aktifitasAdapter

        btnCari.setOnClickListener {
            if (etNIK.text.toString().length<16 || etNIK.text.toString().length>16){
                Toast.makeText(requireActivity(), "NIK harus 16 digit", Toast.LENGTH_SHORT).show()
            }
            else if (etNIK.text.toString().substring(0,4)!="3578"){
                Toast.makeText(requireActivity(), "Aplikasi ini hanya digunakan untuk kepengurusan penduduk Surabaya", Toast.LENGTH_SHORT).show()
            }
            else{
                refreshList(etNIK.text.toString())
            }

        }
    }

    fun refreshList(searchNIK:String){
        val strReq=object : StringRequest(
            Method.GET,
            "$WS_HOST/aktifitas/$searchNIK",
            Response.Listener {
                val obj: JSONArray = JSONArray(it)
                arrAktivitas.clear()
                if(obj.length()!=0) {
                    for (i in 0 until obj.length()) {
                        val o = obj.getJSONObject(i)
                        val id = o.getString("id").toLong()
                        val nik = o.getString("nik")
                        val nama = o.getString("nama")
                        val aktivitas = o.getString("aktivitas").capitalize()
                        val status = o.getString("statusCode").toInt()
                        val m = Aktifitas(id, nik, nama, aktivitas, status)
                        arrAktivitas.add(m)
                    }
                }
                aktifitasAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(context,"error", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)

    }
}