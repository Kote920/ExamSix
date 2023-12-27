package com.example.examsix.domain

data class Item(var id: Int, var number: Int?, var type: Type)
enum class Type {
    NUMBER,
    REMOVE,
    FINGERPRINT
}