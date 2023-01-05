package com.example.laporkami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.laporkami.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    private val coroutine = CoroutineScope(Dispatchers.IO)
    lateinit var binding: ActivityHomeBinding
    lateinit var arrPertanyaan: ArrayList<Pertanyaan>
    lateinit var pertanyaanAdapter: PertanyaanAdapter
    lateinit var loginNow:Users
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        loginNow= intent.getParcelableExtra<Users>("loginNow")!!
        Toast.makeText(this,loginNow.nama,Toast.LENGTH_SHORT).show()
        homePage()
        var height = displayMetrics.heightPixels

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
    fun getIDUser(){
        val strReq=object : StringRequest(
            Method.GET,
            "$WS_HOST/user/getid",
            Response.Listener {
                val obj: JSONObject = JSONObject(it)
                val id=obj.getString("id").toLong()
                Toast.makeText(this,id.toString(),Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Toast.makeText(this,"errorGaNemuAkun", Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)

    }
}