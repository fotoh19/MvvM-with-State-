package com.note.mvvmwithstates.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.note.mvvmwithstates.R
import com.note.mvvmwithstates.viewModels.ProductViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val textView: TextView = findViewById(R.id.product_list_text_view)

        viewModel.productState.observe(this, Observer { state ->
            when (state) {
                is ProductViewModel.ProductState.Loading -> {
                    textView.text = "Loading..."
                }
                is ProductViewModel.ProductState.Success -> {
                    val products = state.products.joinToString("\n") {
                        "${it.name} - ${it.price} EGP"
                    }
                    textView.text = products
                }
                is ProductViewModel.ProductState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.loadProducts()
    }
}
