package com.example.app2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity2 : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        auth = FirebaseAuth.getInstance()

        val loginuser : EditText = findViewById(R.id.loginuser)
        val loginpass : EditText = findViewById(R.id.loginpass)
        val loginbtn : Button = findViewById(R.id.loginbtn)


        loginbtn.setOnClickListener {
            val email = loginuser.text.toString()
            val pass = loginpass.text.toString()
            if(email.isEmpty() ||pass.isEmpty()){
                if(email.isEmpty()){
                    loginuser.error ="Enter you username"
                }
                if(pass.isEmpty()){
                    loginpass.error = "Enter your pass"
                }
                Toast.makeText(this, "enter valid details", Toast.LENGTH_SHORT).show()
            }
         else {
             auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                 Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show()

                 val intent = Intent(this, MainActivity3::class.java)
                 startActivity(intent)


             }.addOnFailureListener {
                 Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

             }
             }
            }
        }






    }
