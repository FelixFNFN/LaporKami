package com.example.laporkami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.laporkami.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val coroutine = CoroutineScope(Dispatchers.IO)
    lateinit var binding: ActivityHomeBinding
    lateinit var arrPertanyaan: ArrayList<Pertanyaan>
    lateinit var pertanyaanAdapter: PertanyaanAdapter
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homePage()

        binding.navBar.setOnItemSelectedListener {
            if(it.itemId==R.id.home_menu){
                homePage()
            }
            else{
                laporPage()
            }
            return@setOnItemSelectedListener true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_logout_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout_menu){
            finish()
        }
        else{
            var profileIntent=Intent(this,ProfileActivity::class.java)
            startActivity(profileIntent)
            homePage()
        }
        return true
    }

    fun homePage() {
        val fragment = HomeFragment()
        var bundle = Bundle()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHome,fragment)
            .setReorderingAllowed(true).commit()
//        coroutine.launch {
//            refresh()
//        }
//        bundle.putParcelable("user", userLogin)
//        bundle.putParcelableArrayList("arrServer", userServer)
//        bundle.putParcelableArrayList("dServer", dserverArr)
//        fragment.arguments = bundle
//        fragment.onDelete = {
//            coroutine.launch {
//                db.dServerDao.deleteServer(it.id_server)
//                db.serverDao.delete(it)
//                homePage()
//            }
//        }
    }

    fun laporPage(){
        val fragment = LaporFragment()
        var bundle = Bundle()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHome,fragment)
            .setReorderingAllowed(true).commit()
    }
}