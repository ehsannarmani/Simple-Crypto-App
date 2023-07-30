package ir.ehsan.asmrcrypto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.ehsan.asmrcrypto.models.BaseModel
import ir.ehsan.asmrcrypto.models.Crypto
import ir.ehsan.asmrcrypto.ui.theme.Green
import ir.ehsan.asmrcrypto.ui.theme.Red
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getListing()
    }
    val data by viewModel.response.collectAsState()

    Column(modifier = Modifier
        .padding(horizontal = 14.dp)
        .verticalScroll(rememberScrollState())
    ) {
        when (val result = data) {
            is BaseModel.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            is BaseModel.Success -> {
                Text(
                    modifier = Modifier.padding(top = 18.dp),
                    text = "24H Currencies",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(result.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().percentChange24h }
                        .reversed()) {
                        Crypto(crypto = it, horizontal = true)
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 18.dp),
                    text = "Top Losers",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(result.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().percentChange24h }) {
                        Crypto(crypto = it, horizontal = true)
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 18.dp),
                    text = "24H Volume Currencies",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(result.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().volume24h }) {
                        Crypto(crypto = it, horizontal = true)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        modifier = Modifier
                            .weight(.5f)
                            .height(60.dp),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            "Sell Now",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(.5f)
                            .height(60.dp),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            "Buy Now",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 18.dp),
                    text = "Top Currencies",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier=Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
                    repeat(result.data.data.cryptoCurrencyList.count()){
                        val crypto = result.data.data.cryptoCurrencyList[it]
                        Crypto(crypto = crypto)
                    }
                }
            }

            is BaseModel.Error -> {
                Text(result.error)
            }

            null -> {

            }
        }
    }
}

@Composable
fun Crypto(crypto: Crypto, horizontal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(8.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.primary)
            .clickable { }
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.size(40.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://s2.coinmarketcap.com/static/img/coins/64x64/${crypto.id}.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null
                )
            }

            Column {
                Text(crypto.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(crypto.symbol, fontSize = 12.sp, color = Color.Gray)
            }
        }
        if (horizontal) {
            Spacer(modifier = Modifier.width(10.dp))
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("${((crypto.quotes.first().price * 100).roundToInt()) / 100.0}$")
            Spacer(modifier = Modifier.height(2.dp))
            val percent = ((crypto.quotes.first().percentChange24h * 100).roundToInt()) / 100.0
            val textColor = if (percent >= 0) Green else Red
            Text(text = "${percent}%", color = textColor)
        }

    }
}