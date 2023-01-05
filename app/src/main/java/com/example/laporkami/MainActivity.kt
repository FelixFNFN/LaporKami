package com.example.laporkami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityMainBinding
import com.example.laporkami.databinding.ActivityRegisterBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btnLogin:Button
    lateinit var btnRegister:Button
    lateinit var arrUser:ArrayList<Users>


    lateinit var binding: ActivityMainBinding

    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        etEmail=findViewById(R.id.etEmail)
//        etPassword=findViewById(R.id.etPassword)
//        btnLogin=findViewById(R.id.btnLogin)
//        btnRegister=findViewById(R.id.btnRegister)

        var intentHome=Intent(this,HomeActivity::class.java)
        var intentRegister=Intent(this,RegisterActivity::class.java)

        var MainLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result:ActivityResult ->
            if(result != null){

            }
        }

        binding.btnLogin.setOnClickListener {
            if(binding.etEmail.text.toString()!="" && binding.etPassword.text.toString()!=""){


                val strReq=object : StringRequest(
                    Method.POST,
                    "$WS_HOST/user/login",
                    Response.Listener {
                        val obj:JSONObject = JSONObject(it) // disini diubah menggunakan objek karena menggunakan auth
                        if (obj.length()!=0) {
                            val email = obj.getString("email")
                            val password = obj.getString("password")
                            if (binding.etEmail.text.toString()==email && binding.etPassword.text.toString() == password){
                                val loginUser=Users(obj.getString("id").toLong(),obj.getString("email"),obj.getString("nama"),obj.getString("password"),obj.getString("noTelp"))
                                intentHome.putExtra("loginNow",loginUser)
                                MainLauncher.launch(intentHome)
                                binding.etEmail.setText("")
                                binding.etPassword.setText("")
                            }
                            else{
                                Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this, "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                        }

                    },
                    Response.ErrorListener {
                        Toast.makeText(this,"error Login",Toast.LENGTH_SHORT).show()
                    }
                ){
                    override fun getParams(): MutableMap<String, String>? {
                        val params = HashMap<String,String>()
                        params["email"] = binding.etEmail.text.toString()
                        params["password"] = binding.etPassword.text.toString()
                        return params
                    }
                }
                val queue:RequestQueue = Volley.newRequestQueue(this)
                queue.add(strReq)

            }

        }

        var registerLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result:ActivityResult ->
            if(result != null){

            }
        }

        binding.btnRegister.setOnClickListener {
            registerLauncher.launch(intentRegister)
        }
    }
}