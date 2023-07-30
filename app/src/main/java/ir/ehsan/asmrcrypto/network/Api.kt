package ir.ehsan.asmrcrypto.network

import ir.ehsan.asmrcrypto.models.ApiBaseModel
import ir.ehsan.asmrcrypto.models.CryptoCurrencyList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("listing")
    suspend fun getListing(
        @Query("start")
        start:Int,
        @Query("limit")
        limit:Int,
        @Query("sortBy")
        sortBy:String = "market_cap",
        @Query("convert")
        convert:String = "USD",
        @Query("cryptoType")
        cryptoType:String = "all",
        @Query("tagType")
        tagType:String = "all",
        @Query("audited")
        audited:Boolean = false,
        @Query("aux", encoded = false)
        aux:String = "ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap"
    ):Response<ApiBaseModel<CryptoCurrencyList>>
}