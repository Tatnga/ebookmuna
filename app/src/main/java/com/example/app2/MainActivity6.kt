package com.example.app2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class MainActivity6 : AppCompatActivity() {

private lateinit var auth: FirebaseAuth
private lateinit var database : FirebaseDatabase
private lateinit var storage : FirebaseStorage
private lateinit var selectedimg : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main6)

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
    }
}