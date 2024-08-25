package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputFirstNameET: EditText
    private lateinit var inputLastNameET: EditText
    private lateinit var inputAddressET: EditText
    private lateinit var inputPhoneNumberET: EditText
    private lateinit var saveBTN: Button
    private val persons: MutableList<Person> = mutableListOf()
    private lateinit var personsLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputFirstNameET = findViewById(R.id.inputFirstNameET)
        inputLastNameET = findViewById(R.id.inputLastNameET)
        inputAddressET = findViewById(R.id.inputAddressET)
        inputPhoneNumberET = findViewById(R.id.inputPhoneNumberET)
        saveBTN = findViewById(R.id.saveBTN)

        personsLV = findViewById(R.id.personsLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, persons)
        personsLV.adapter = adapter
        personsLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val person = adapter.getItem(position)
                val intent = Intent(this, OutputPersonDataActivity::class.java)
                intent.putExtra(Person::class.java.simpleName, person)
                startActivity(intent)
            }

            saveBTN.setOnClickListener {

                val firstName = inputFirstNameET.text.toString().lowercase().replace(" ", "")
                val lastName = inputLastNameET.text.toString().lowercase().replace(" ", "")
                val phoneNumber = inputPhoneNumberET.text.toString().replace(" ", "")
                if (testOfData(
                        this,
                        firstName,
                        lastName,
                        inputAddressET.text.toString(),
                        phoneNumber
                    ) == false
                )
                    return@setOnClickListener
                persons.add(
                    Person(
                        firstName.first().uppercase() + firstName.drop(1),
                        lastName.first().uppercase() + lastName.drop(1),
                        inputAddressET.text.toString(),
                        phoneNumber
                    )
                )
                adapter.notifyDataSetChanged()
                inputFirstNameET.text.clear()
                inputLastNameET.text.clear()
                inputAddressET.text.clear()
                inputPhoneNumberET.setText(R.string.input_phone_number_default)
            }
        }
    }