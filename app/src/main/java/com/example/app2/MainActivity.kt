 package com.example.app2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 class MainActivity : AppCompatActivity() {


     private lateinit var edituser: EditText
     private lateinit var edituser2: EditText
     private lateinit var edituser3: EditText
     private lateinit var edituser5: EditText
     private lateinit var signupbtn: Button
     private lateinit var loginbtn: Button

     private lateinit var dbRef: DatabaseReference
     private lateinit var auth: FirebaseAuth

     @SuppressLint("MissingInflatedId")
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)


         edituser = findViewById(R.id.loginuser)
         edituser2 = findViewById(R.id.edituser2)
         edituser3 = findViewById(R.id.edituser3)
         edituser5 = findViewById(R.id.edituser5)
         loginbtn = findViewById(R.id.loginbtn2)
         signupbtn = findViewById(R.id.loginbtn)

         auth = FirebaseAuth.getInstance()
         dbRef = FirebaseDatabase.getInstance().getReference("users")

         this.loginbtn.setOnClickListener {
             val intent = Intent(this, MainActivity2::class.java)
             startActivity(intent)
         }
         this.signupbtn.setOnClickListener {
             saveEmployeeData()
             saveuser()
         }
     }
     private fun saveuser() {
         val email = edituser5.text.toString()
         val password = edituser3.text.toString()
         auth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener {
                 Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show()

                 val intent = Intent(this, MainActivity2::class.java)
                 startActivity(intent)


             }.addOnFailureListener {
                 Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

             }
     }
     private fun saveEmployeeData() {

         val name = edituser.text.toString()
         val username = edituser2.text.toString()
         val password = edituser3.text.toString()
         val email = edituser5.text.toString()
         if (name.isEmpty()) {
             edituser.error = "Please enter name"
         }
         if (username.isEmpty()) {
             edituser2.error = "Please enter name"
         }
         if (password.isEmpty()) {
             edituser3.error = "Please enter name"
         }
         if (email.isEmpty()) {
             edituser5.error = "Please enter name"
         } else {
             val users = user(name, username, password, email)

             dbRef.child(name).setValue(users)
                 .addOnCompleteListener {
                     Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show()

                     val intent = Intent(this, MainActivity2::class.java)
                     startActivity(intent)


                 }.addOnFailureListener {
                     Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                 }

         }


     }

 }

