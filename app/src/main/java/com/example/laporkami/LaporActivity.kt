package com.example.laporkami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityHomeBinding
import com.example.laporkami.databinding.ActivityLaporBinding
import org.json.JSONObject

class LaporActivity : AppCompatActivity() {

    lateinit var binding: ActivityLaporBinding

    lateinit var loginNow: Users
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lapor)

        loginNow= intent.getParcelableExtra<Users>("loginNow")!!

        binding = ActivityLaporBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.btnLapor.setOnClickListener {
            if (binding.etSubjek.text.toString() == ""){
                Toast.makeText(this, "Subjek tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etDetailLaporan.text.toString() == ""){
                Toast.makeText(this, "Detail laporan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                val strReq=object : StringRequest(
                    Method.POST,
                    "$WS_HOST/laporan/insert",
                    Response.Listener {
                        Toast.makeText(this, "input laporan sukses", Toast.LENGTH_SHORT).show()
                        finish()
                    },
                    Response.ErrorListener {
                        Toast.makeText(this,"error Insert",Toast.LENGTH_SHORT).show()
                    }
                ){
                    override fun getParams(): MutableMap<String, String>? {
                        val params = HashMap<String,String>()
                        params["subjek"] = binding.etSubjek.text.toString()
                        params["detail"] = binding.etDetailLaporan.text.toString()
                        params["id_user"] = loginNow.id.toString()
                        return params
                    }
                }
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(strReq)
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.back_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.back_menu){
            finish()
        }
        return true
    }

}