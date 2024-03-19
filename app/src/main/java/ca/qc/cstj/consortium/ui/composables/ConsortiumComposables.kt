package ca.qc.cstj.consortium.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun InventoryItemCard(name:String,@DrawableRes symbol:Int,quantity:Float) {
    var sliderPosition by remember { mutableFloatStateOf(quantity) }
    Row (modifier= Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
    Column {
        Text(text = name)
        Image(painter = painterResource(id= symbol), contentDescription = name)
        Text(text = "%.2f".format(quantity))
    }
        Slider(value = sliderPosition, onValueChange = {sliderPosition = it })

    }

}

