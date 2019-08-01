package artezyazilim.com.addapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        activeContext = this

        println(">>${KayitliVeri("KullanıcıAdı")}")

    }

    fun add(view: View) {
        val newact = Intent(applicationContext, AddActivity::class.java)
        startActivity(newact)

    }

    fun delete(view: View) {


    }

    fun edit(view: View) {


    }




}
