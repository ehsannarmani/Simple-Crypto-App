package ir.ehsan.asmrcrypto.repositories

import ir.ehsan.asmrcrypto.models.ApiBaseModel
import ir.ehsan.asmrcrypto.models.BaseModel
import ir.ehsan.asmrcrypto.models.CryptoCurrencyList
import ir.ehsan.asmrcrypto.network.Api
import java.lang.Exception

class CryptoRepoImpl(private val api: Api):CryptoRepo {
    override suspend fun getListing(): BaseModel<ApiBaseModel<CryptoCurrencyList>> {
        try {
            api.getListing(
                start = 1,
                limit = 100
            ).also {
                if (it.isSuccessful){
                    return BaseModel.Success(data = it.body()!!)
                }else{
                    return BaseModel.Error("We have an error")
                }
            }
        }catch (e:Exception){
            return BaseModel.Error(e.message.toString())
        }
    }
}