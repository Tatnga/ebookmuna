package com.example.app2

import android.net.Uri

data class book(
    val name:  String? = null, val author: String? = null,
    var description: String? = null,
    val category: String? = null,
    val uid: String? = null,
    val imageMap: Map<String, Uri>
)