package ir.ehsan.asmrcrypto.repositories

import ir.ehsan.asmrcrypto.models.ApiBaseModel
import ir.ehsan.asmrcrypto.models.BaseModel
import ir.ehsan.asmrcrypto.models.CryptoCurrencyList

interface CryptoRepo {
    suspend fun getListing():BaseModel<ApiBaseModel<CryptoCurrencyList>>
}