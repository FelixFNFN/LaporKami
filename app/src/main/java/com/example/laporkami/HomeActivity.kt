package com.example.laporkami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val coroutine = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homePage()

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

    }