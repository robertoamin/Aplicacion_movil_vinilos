package com.example.vinilos.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.vinilos.MainActivity
import com.example.vinilos.R
import com.example.vinilos.ui.album.AlbumDetailActivity

//@AndroidEntryPoint

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val cardUsers: CardView = findViewById(R.id.card_usuarios)
        cardUsers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "AlbumFragment")
            startActivity(intent)
        }

        val cardColectors: CardView = findViewById(R.id.card_coleccionistas)
        cardColectors.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "ColeccionistaFragment")
            startActivity(intent)
        }
    }



}