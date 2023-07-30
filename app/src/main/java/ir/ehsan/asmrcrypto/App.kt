package ir.ehsan.asmrcrypto

import android.app.Application
import ir.ehsan.asmrcrypto.network.Api
import ir.ehsan.asmrcrypto.repositories.CryptoRepo
import ir.ehsan.asmrcrypto.repositories.CryptoRepoImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(module {
                single {
                    Retrofit.Builder()
                        .baseUrl("https://api.coinmarketcap.com/data-api/v3/cryptocurrency/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                single {
                    val retrofit:Retrofit = get()
                    retrofit.create(Api::class.java)
                }
                single {
                    val api:Api = get()
                    CryptoRepoImpl(api)
                } bind CryptoRepo::class
            })
        }

    }
}