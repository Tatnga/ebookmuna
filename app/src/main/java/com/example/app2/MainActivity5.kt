package com.example.app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class MainActivity5 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var imageView3: ImageView
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var uri: Uri
    private lateinit var edituser1: EditText
    private lateinit var edituser2: EditText
    private lateinit var edituser3: EditText
    private lateinit var edituser4: EditText
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main5)

        edituser1 = findViewById(R.id.edituser1)
        edituser2 = findViewById(R.id.edituser2)
        edituser3 = findViewById(R.id.edituser3)
        edituser4 = findViewById(R.id.edituser4)
        button5 = findViewById(R.id.button5)
        imageView3 = findViewById(R.id.imageView3)
        button6 = findViewById(R.id.button6)
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("books")

        val gala = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView3.setImageURI(it)
                if (it != null) {
                    uri =it
                }
            }
        )
        button5.setOnClickListener {
            gala.launch("image/*")
        }

        button6.setOnClickListener {

            storage.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener {task->
                     task.metadata!!.reference!!.downloadUrl
                         .addOnSuccessListener {uri ->
                             val uid = FirebaseAuth.getInstance().currentUser!!.uid
                             val imageMap = mapOf(
                                 "url" to uri
                             )
                             val name = edituser1.text.toString()
                             val author = edituser2.text.toString()
                             val description = edituser3.text.toString()
                             val category = edituser4.text.toString()
                             if (name.isEmpty()) {
                                 edituser1.error = "Please enter name"
                             }
                             if (author.isEmpty()) {
                                 edituser2.error = "Please enter name"
                             }
                             if (description.isEmpty()) {
                                 edituser3.error = "Please enter name"
                             }
                             if (category.isEmpty()) {
                                 edituser4.error = "Please enter name"
                             } else {
                                 val books = book(name, author, description, category,uid,imageMap)
                             val databaseReference = FirebaseDatabase.getInstance().getReference("userImages")
                                 dbRef.child(name).setValue(books)
                                 .addOnSuccessListener {
                                     Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                                 }
                                 .addOnFailureListener() {
                                     Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
                                 }




                         }
                }




                    .addOnCompleteListener {
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



