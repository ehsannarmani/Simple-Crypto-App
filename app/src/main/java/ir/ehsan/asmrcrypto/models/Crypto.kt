package ir.ehsan.asmrcrypto.models

data class Crypto(
    val id:Int,
    val name:String,
    val symbol:String,
    val slug:String,
    val cmcRank:Int,
    val marketPairCount:Int,
    val circulatingSupply:Double,
    val selfReportedCirculatingSupply:Double,
    val totalSupply:Double,
    val ath:Double,
    val atl:Double,
    val high24h:Double,
    val low24h:Double,
    val isActive:Int,
    val lastUpdated:String,
    val dateAdded:String,
    val quotes:List<Quote>,
    val isAudited:Boolean
)
