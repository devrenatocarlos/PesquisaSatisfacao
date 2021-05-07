package com.example.pesquisasatisfacao

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.example.pesquisasatisfacao.MESSAGE"

class MainActivity : AppCompatActivity() {
   private var sp : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       sp = getRoBuildProduct()
       val  button  = findViewById<Button>(R.id.button)
        button.setBackgroundColor(Color.parseColor("#00ff00"))
    }

    private fun getRoBuildProduct(): String {
        return Class.forName("android.os.SystemProperties").let {
            it.getDeclaredMethod(
                "get",
                String::class.java
            ).invoke(null, "ro.build.product") as String
        }
    }

    fun sendMessage(view: View) {
        val nameUser = findViewById<EditText>(R.id.et_name)
        val message = nameUser.text.toString() + " o Cesar agradece a sua resposta. Você realizou a pesquisa utilizando o device  "+ sp
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}

