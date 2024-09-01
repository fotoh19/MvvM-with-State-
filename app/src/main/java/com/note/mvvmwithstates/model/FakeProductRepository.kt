package com.note.mvvmwithstates.model

class FakeProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop", 15000.0),
            Product(2, "Smartphone", 8000.0),
            Product(3, "Tablet", 4000.0)
        )
    }
}
