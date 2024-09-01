package com.note.mvvmwithstates.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.note.mvvmwithstates.model.FakeProductRepository
import com.note.mvvmwithstates.model.Product

class ProductViewModel : ViewModel() {
    private val repository = FakeProductRepository()

    private val _productState = MutableLiveData<ProductState>()
    val productState: LiveData<ProductState> get() = _productState

    fun loadProducts() {
        _productState.value = ProductState.Loading

        try {
            val products = repository.getProducts()
            _productState.value = ProductState.Success(products)
        } catch (e: Exception) {
            _productState.value = ProductState.Error("Failed to load products")
        }
    }
//  here the states for product what happend
    sealed class ProductState {
        data object Loading : ProductState()
        data class Success(val products: List<Product>) : ProductState()
        data class Error(val message: String) : ProductState()
    }
}
