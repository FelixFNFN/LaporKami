package com.example.laporkami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btnLogin:Button
    lateinit var btnRegister:Button
    lateinit var arrUser:ArrayList<Users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        btnRegister=findViewById(R.id.btnRegister)

        var intentHome=Intent(this,HomeActivity::class.java)
        var intentRegister=Intent(this,RegisterActivity::class.java)

        var MainLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result:ActivityResult ->
            if(result != null){

            }
        }

        btnLogin.setOnClickListener {
            if(etEmail.text.toString()!="" && etPassword.text.toString()!=""){
                MainLauncher.launch(intentHome)
                etEmail.setText("")
                etPassword.setText("")
            }

        }

        var registerLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result:ActivityResult ->
            if(result != null){

            }
        }

        btnRegister.setOnClickListener {
            registerLauncher.launch(intentRegister)
        }
    }
}