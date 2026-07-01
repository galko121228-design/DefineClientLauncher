package com.defineclient.launcher

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mcPackage = "com.mojang.minecraftpe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.statusText)
        val button = findViewById<Button>(R.id.playButton)

        val installed = isInstalled()

        status.text = if (installed) {
            "✅ Minecraft установлен"
        } else {
            "❌ Minecraft НЕ установлен"
        }

        button.isEnabled = installed

        button.setOnClickListener {
            val intent = packageManager.getLaunchIntentForPackage(mcPackage)
            startActivity(intent)
        }
    }

    private fun isInstalled(): Boolean {
        return try {
            packageManager.getPackageInfo(mcPackage, 0)
            true
        } catch (e: Exception) {
            false
        }
    }
}
