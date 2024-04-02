package ca.qc.cstj.consortium.ui.composables


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Constants
import ca.qc.cstj.consortium.core.toDateFormat
import ca.qc.cstj.consortium.models.Delivery
import ca.qc.cstj.consortium.models.Trader

@Composable
fun InventoryItemCard(
    element:String, @DrawableRes symbol:Int,
    quantity:Float,
    update: (String,Float) -> Unit,
    slider:Float) {
    var sliderPosition by remember { mutableFloatStateOf(slider) }
    Row (modifier= Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
    Column {
        Text(text = element, textAlign = TextAlign.Center)
        Row {
            Image(painter = painterResource(id= symbol), contentDescription = element, modifier = Modifier.size(64.dp))

            Slider(value = sliderPosition, onValueChange = {
                sliderPosition = it
                update(element,it)

                                                           }, valueRange = 0f..quantity, enabled = (quantity>0.0))
        }

        Text(text = "%.2f".format(sliderPosition), textAlign = TextAlign.Center)

    }


    }

}

@Composable
fun DeliveryItemCard(del:Delivery){
    Card ( colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(96.dp)

    ){  Column (modifier=Modifier.fillMaxWidth()){
            Text(text = del.deliveryDate.toDateFormat() ,modifier = Modifier.align(alignment = Alignment.CenterHorizontally), textAlign = TextAlign.Center)
            Row (modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 8.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Image(painter = painterResource(id= R.drawable.i), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                    Text(text = "%.2f".format(del.iaspyx) )
                }
                Column {
                    Image(painter = painterResource(id= R.drawable.sm), contentDescription = Constants.ELEMENTS[1], modifier = Modifier.size(48.dp))
                    Text(text = "%.2f".format(del.smiathil)  )

                }
                Column {
                    Image(painter = painterResource(id= R.drawable.ja), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                    Text(text = "%.2f".format(del.jasmalt) )

                }
                Column {
                    Image(painter = painterResource(id= R.drawable.ve), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                    Text(text = "%.2f".format(del.vethyx)  )

                }
                Column {
                    Image(painter = painterResource(id= R.drawable.b), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                    Text(text = "%.2f".format(del.blierium)  )

                }
            }
        }
    }

}


@Composable
fun TraderInventory(trader:Trader){
    Card ( colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(96.dp)

    ){  Column (modifier=Modifier.fillMaxWidth()){
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)

            .padding(start = 8.dp, end = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(painter = painterResource(id= R.drawable.i), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                Text(text = "%.2f".format(trader.iaspyx) )
            }
            Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(painter = painterResource(id= R.drawable.sm), contentDescription = Constants.ELEMENTS[1], modifier = Modifier.size(48.dp))
                Text(text = "%.2f".format(trader.smiathil)  )

            }
            Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(painter = painterResource(id= R.drawable.ja), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                Text(text = "%.2f".format(trader.jasmalt) )

            }
            Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(painter = painterResource(id= R.drawable.ve), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                Text(text = "%.2f".format(trader.vethyx)  )

            }
            Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                Image(painter = painterResource(id= R.drawable.b), contentDescription = Constants.ELEMENTS[0], modifier = Modifier.size(48.dp))
                Text(text = "%.2f".format(trader.blierium)  )

            }
        }
    }
    }

}



