package kg.tutorialapp.hw_46

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var settings: SharedPreferences
    private lateinit var etText: EditText
    private lateinit var tvText: TextView
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    companion object{
        const val APP_PREFERENCES = "mySettings"
        const val APP_PREFERENCES_TEXT = "Text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        etText = findViewById(R.id.et_text)
        tvText = findViewById(R.id.tv_text)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        if (settings.contains(APP_PREFERENCES_TEXT)) {
            tvText.setText(settings.getString(APP_PREFERENCES_TEXT, ""))
        }

        setup()
    }

    private fun setup() {
        btnSave.setOnClickListener {
            val text = etText.text.toString()
            val editor: SharedPreferences.Editor = settings.edit()
            editor.putString(APP_PREFERENCES_TEXT, text)
            editor.apply()
            tvText.setText(settings.getString(APP_PREFERENCES_TEXT, ""))
        }

        btnDelete.setOnClickListener {
            val editor: SharedPreferences.Editor = settings.edit()
            editor.remove(APP_PREFERENCES_TEXT)
            editor.clear()
            editor.apply()
        }
    }
}