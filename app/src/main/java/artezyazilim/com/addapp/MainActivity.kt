package artezyazilim.com.addapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activeContext = this

        VeriKaydet("Test1", "KullanıcıAdı")

        try{

            val myDatabase = this.openOrCreateDatabase("KullaniciDb", android.content.Context.MODE_PRIVATE, null)
          //  myDatabase.execSQL("CREATE TABLE IF NOT EXISTS kullanici (username VARCHAR,password VARCHAR,name VARCHAR,surname VARCHAR)")
          //  myDatabase.execSQL("INSERT INTO kullanici (username,password,name,surname) VALUES ('tasmelike','123','Melike','Tas')")
          //  myDatabase.execSQL("ALTER TABLE kullanici ADD COLUMN yetki INT(1)")
         //   myDatabase.execSQL("UPDATE SET kullanici (yetki) VALUES (1) WHERE username = 'admin'")
         //  myDatabase.execSQL("UPDATE kullanici SET yetki =1 WHERE username = 'admin'")


            val cursor = myDatabase.rawQuery("SELECT * FROM kullanici",null)

            val usernameIndex = cursor.getColumnIndex("username")
            val passwordIndex = cursor.getColumnIndex("password")
            val nameIndex = cursor.getColumnIndex("name")
            val surnameIndex = cursor.getColumnIndex("surname")
            val yetkiIndex = cursor.getColumnIndex("yetki")

            cursor.moveToFirst()

            while(cursor!=null)
            {
                println("+++ USERName: ${cursor.getString(usernameIndex)}")
                println("+++ PASSWORD: ${cursor.getString(passwordIndex)}")
                println("+++ Name: ${cursor.getString(nameIndex)}")
                println("+++ SURNAME: ${cursor.getString(surnameIndex)}")
                println("+++ YEtki: ${cursor.getInt(yetkiIndex)}")

                cursor.moveToNext()
            }
            if(cursor!=null) {
                cursor!!.close()
            }

        }

        catch (e: Exception){
            e.printStackTrace()
        }
    }


    fun login (view: View) {
        sharedPreferences = this@MainActivity.getSharedPreferences(applicationContext.packageName, Context.MODE_PRIVATE)

         val myusername = myText.text.toString()
         sharedPreferences.edit().putString("userName", myusername).apply()
         val isim = sharedPreferences.getString("userName",null)
         println("+++ KULLANICI ADI: $isim")

        val mypass = passwordText.text.toString()
        sharedPreferences.edit().putString("pass", mypass).apply()
        val sifre = sharedPreferences.getString(("pass"),null)
        println("+++PASSWORD: $sifre")


        val myDatabase = this.openOrCreateDatabase("KullaniciDb", android.content.Context.MODE_PRIVATE, null)
        val cursor = myDatabase.rawQuery("SELECT COUNT(*) AS KULLANICI FROM kullanici WHERE username = '$myusername' AND password = '$mypass' ",null)
        val countId = cursor.getColumnIndex("KULLANICI")

        val yetkiadmin = myDatabase.rawQuery("SELECT yetki FROM  kullanici  WHERE username= '$myusername' ",null)
        val yetki1 = yetkiadmin.getColumnIndex("yetki")



     //   println(">>$usernameId -- $cursor -- ${cursor.columnCount} -- ${cursor.getString(usernameId)}")
        cursor.moveToFirst()
        yetkiadmin.moveToFirst()
                if( cursor.getInt(countId) == 1 && cursor.getInt(yetki1) == 1)
                {
                    Toast.makeText(applicationContext, "GİRİŞ BAŞARILI", Toast.LENGTH_LONG).show()

                        val newact = Intent(applicationContext, SecondActivity::class.java)
                        startActivity(newact)


                }

              /*  else if(cursor.getInt(countId) == 1)
                {
                    Toast.makeText(applicationContext, "GİRİŞ BAŞARILI", Toast.LENGTH_LONG).show()

                    val newact = Intent(applicationContext, UserActivity::class.java)
                    startActivity(newact)


                }*/
                else{
                    Toast.makeText(applicationContext, "Kullanıcı Adı veya Şifre Yanlış", Toast.LENGTH_LONG).show()
                    }


        }
}
