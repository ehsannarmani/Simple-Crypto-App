package ir.ehsan.asmrcrypto.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ehsan.asmrcrypto.models.ApiBaseModel
import ir.ehsan.asmrcrypto.models.BaseModel
import ir.ehsan.asmrcrypto.models.CryptoCurrencyList
import ir.ehsan.asmrcrypto.repositories.CryptoRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(),KoinComponent {
    private val repo:CryptoRepo by inject()

    private val _response:MutableStateFlow<BaseModel<ApiBaseModel<CryptoCurrencyList>>?> = MutableStateFlow(null)
    val response = _response.asStateFlow()

    fun getListing(){
        viewModelScope.launch {
            _response.update { BaseModel.Loading }
            repo.getListing().also { res->
                _response.update { res }
            }
        }
    }
}