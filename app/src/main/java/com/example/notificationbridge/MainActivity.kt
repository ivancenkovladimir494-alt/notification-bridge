package com.example.notificationbridge

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
        }

        val title = android.widget.TextView(this).apply {
            text = "Выбор приложений для перехвата:"
            textSize = 18f
            setPadding(0, 0, 0, 30)
        }
        layout.addView(title)

        // Кнопка для выдачи системного разрешения на чтение уведомлений
        val btnPermission = Button(this).apply {
            text = "1. Дать разрешение на чтение уведомлений"
            setOnClickListener {
                val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                startActivity(intent)
            }
        }
        layout.addView(btnPermission)

        // Отступ
        val spacer = android.widget.TextView(this).apply {
            text = "\nВыберите нужные приложения:"
            textSize = 14f
        }
        layout.addView(spacer)

        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        // Чекбокс для Kaspi
        val cbKaspi = CheckBox(this).apply {
            text = "Kaspi.kz (kz.kaspi.app)"
            isChecked = prefs.getBoolean("kz.kaspi.app", false)
            setOnCheckedChangeListener { _, isChecked ->
                prefs.edit().putBoolean("kz.kaspi.app", isChecked).apply()
            }
        }
        layout.addView(cbKaspi)

        // Чекбокс для PrivatBank
        val cbPrivat = CheckBox(this).apply {
            text = "PrivatBank (com.privatbank.app)"
            isChecked = prefs.getBoolean("com.privatbank.app", false)
            setOnCheckedChangeListener { _, isChecked ->
                prefs.edit().putBoolean("com.privatbank.app", isChecked).apply()
            }
        }
        layout.addView(cbPrivat)

        // Чекбокс для Glovo
        val cbGlovo = CheckBox(this).apply {
            text = "Glovo (com.glovo)"
            isChecked = prefs.getBoolean("com.glovo", false)
            setOnCheckedChangeListener { _, isChecked ->
                prefs.edit().putBoolean("com.glovo", isChecked).apply()
            }
        }
        layout.addView(cbGlovo)

        setContentView(layout)
    }
}
