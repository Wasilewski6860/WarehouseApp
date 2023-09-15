package com.example.newwarehouseapp.domain.models

data class Supplier(
    var id: Int = 0,
    var name: String,
    var password : String,
    var phone : String,
    var email: String
)