package artezyazilim.com.addapp

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        activeContext = this


    }
        @SuppressLint("SimpleDateFormat")
        fun buttonClick (view: View){

            /*val cal = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd.MM.yyyy")
            val dateStr: String = dateFormat.format(cal.time)*/

            val username1 = editText.text.toString()
            val password1 = editText2.text.toString()
            val name1 = editText3.text.toString()
            val surname1 = editText4.text.toString()


            try{

                val myDatabase = this.openOrCreateDatabase("KullaniciDb",android.content.Context.MODE_PRIVATE, null)
               // myDatabase.execSQL("INSERT INTO kullanici (username,password,name,surname) VALUES ('$username','$password', '$name', '$username') ")

                myDatabase.execSQL("INSERT INTO kullanici (username,password,name,surname) VALUES ('$username1','$password1', '$name1', '$surname1') ")


           /*     val statement =

                statement.bindString(1,username)
                statement.bindString(2,password)
                statement.bindString(3,name)
                statement.bindString(4,surname)
                statement.execute()
*/
              //  val myDatabase = this.openOrCreateDatabase("KullaniciDb",android.content.Context.MODE_PRIVATE, null)

               val cursor = myDatabase.rawQuery("SELECT * FROM kullanici",null)

                val usernameIndex = cursor.getColumnIndex("username")
                val passwordIndex = cursor.getColumnIndex("password")
                val nameIndex = cursor.getColumnIndex("name")
                val surnameIndex = cursor.getColumnIndex("surname")

                cursor.moveToFirst()

                while(cursor!=null)
                {
                    println("+++ USERNAME: ${cursor.getString(usernameIndex)}")
                    println("+++ PASSWORD: ${cursor.getString(passwordIndex)}")
                    println("+++ NAME: ${cursor.getString(nameIndex)}")
                    println("+++ SURNAME: ${cursor.getString(surnameIndex)}")

                    cursor.moveToNext()
                }
                if(cursor!=null) {
                    cursor!!.close()
                }

            }

            catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, "Kayıt Başarılı", Toast.LENGTH_LONG).show()
            val newact = Intent(applicationContext, SecondActivity::class.java)
            startActivity(newact)

        }
}
