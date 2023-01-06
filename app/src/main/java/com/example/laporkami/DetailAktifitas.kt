package com.example.laporkami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laporkami.databinding.ActivityDetailAktifitasBinding
import com.example.laporkami.databinding.ActivityHomeBinding

class DetailAktifitas : AppCompatActivity() {
    lateinit var binding: ActivityDetailAktifitasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAktifitasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var AktifitasSelected=intent.getParcelableExtra<Aktifitas>("selectedAktifitas")

        binding.tvNamaAktifitas.setText(AktifitasSelected?.aktivitas)
        binding.tvNamaTerurus.setText(AktifitasSelected?.nama)
        binding.tvNIKTerurus.setText(AktifitasSelected?.nik)
        if(AktifitasSelected?.statusCode == 3){
            binding.tvDetailStatus.setTextColor(getColor(R.color.success))
        }
        else if(AktifitasSelected?.statusCode == 0){
            binding.tvDetailStatus.setTextColor(getColor(R.color.failed))
        }
        val status:String
        if(AktifitasSelected?.statusCode==3){
            status="Selesai"
        }
        else if(AktifitasSelected?.statusCode==2){
            status="Diproses"
        }
        else if(AktifitasSelected?.statusCode==1){
            status="Terkirim"
        }
        else{
            status="Gagal"
        }
        binding.tvDetailStatus.setText(status)

        binding.btnBackHome.setOnClickListener {
            finish()
        }

    }
}