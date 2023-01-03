package com.example.laporkami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
=======
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityHomeBinding
import com.example.laporkami.databinding.ActivityRegisterBinding
import org.json.JSONArray

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    val WS_HOST = "http://10.0.2.2:8000/api"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var intentLogin = Intent(this,RegisterActivity::class.java)

        binding.btntoLogin.setOnClickListener {
            finish()
        }
        
        binding.btnRegisterPage.setOnClickListener {
            val strReq=object : StringRequest(
                Method.POST,
                "$WS_HOST/user/insert",
                Response.Listener {
                    Toast.makeText(this, "input data", Toast.LENGTH_SHORT).show()
                    finish()
                },
                Response.ErrorListener {
                    Toast.makeText(this,"error register",Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String,String>()
                    params["email"] = binding.etEmailRegis.text.toString()
                    params["nama"] = binding.etNamaLengkapRegis.text.toString()
                    params["noTelp"] = binding.etNomortTelponRegis.text.toString()
                    params["password"] = binding.etPasswordRegis.text.toString()
                    return params
                }
            }
            val queue:RequestQueue = Volley.newRequestQueue(this)
            queue.add(strReq)
        }
    }

>>>>>>> Stashed changes
}