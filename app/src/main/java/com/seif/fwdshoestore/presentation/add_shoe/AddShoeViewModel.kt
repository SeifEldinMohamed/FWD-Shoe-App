package com.seif.fwdshoestore.presentation.add_shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seif.fwdshoestore.domain.model.Shoe

class AddShoeViewModel : ViewModel() {
    private val mutableShoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> get() = mutableShoe

    fun addShoe(shoe: Shoe) {
        mutableShoe.value = shoe
    }
}