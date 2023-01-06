package com.example.laporkami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.laporkami.databinding.ActivityDetailAktifitasBinding
import com.example.laporkami.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var loginNow=intent.getParcelableExtra<Users>("loginNow")

        binding.tvNama.setText(loginNow?.nama)
        binding.tvEmail.setText(loginNow?.email)
        binding.tvNoTelp.setText(loginNow?.noTelp)

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