package com.example.serializableparcelable

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OutputPersonDataActivity : AppCompatActivity() {
    private lateinit var outputNameMeaningTV: TextView
    private lateinit var outputAddressMeaningTV: TextView
    private lateinit var outputPhoneNumberMeaningTV: TextView
    private lateinit var buttonBackBNT: Button
    var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_output_person_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        outputNameMeaningTV = findViewById(R.id.outputNameMeaningTV)
        outputAddressMeaningTV = findViewById(R.id.outputAddressMeaningTV)
        outputPhoneNumberMeaningTV = findViewById(R.id.outputPhoneNumberMeaningTV)
        buttonBackBNT = findViewById(R.id.buttonBackBNT)
        buttonBackBNT.setOnClickListener { finish() }
        person = intent.extras?.getSerializable(Person::class.java.simpleName) as Person?
        outputNameMeaningTV.text = person?.firstName + " " + person?.lastName
        outputAddressMeaningTV.text = person?.address
        outputPhoneNumberMeaningTV.text =
            person?.phoneNumber?.substring(0, 2) + " " + person?.phoneNumber?.substring(2, 5) +
                    " " + person?.phoneNumber?.substring(5, 8) + "-" + person?.phoneNumber?.substring(8, 10) +
                    "-" + person?.phoneNumber?.substring(10, 12)
    }
}