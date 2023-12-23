package com.codingwithsandeep.e_commerceapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithsandeep.e_commerceapp.model.ProductListItem
import com.codingwithsandeep.e_commerceapp.repository.ProductRepository
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    private val _productsResponse: MutableLiveData<List<ProductListItem>?> = MutableLiveData()
    val productList: LiveData<List<ProductListItem>?>
        get() = _productsResponse

    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    lateinit var disposable: Disposable

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllProducts() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = productRepository.getAllProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _productsResponse.postValue(response.body())
                    isLoading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private val isLoading = MutableLiveData<Boolean>()

    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}