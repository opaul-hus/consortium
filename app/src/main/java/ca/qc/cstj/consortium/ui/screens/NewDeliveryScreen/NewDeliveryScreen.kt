

package ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.core.Constants
import ca.qc.cstj.consortium.ui.composables.InventoryItemCard
import ca.qc.cstj.consortium.ui.navigation.Screen


@Composable

fun NewDeliveryScreen(
    navController: NavHostController,
    viewModel : NewDeliveryScreenViewModel= viewModel()

)
{
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.eventsFlow.collect { event ->
            when(event) {
                is NewDeliveryScreenViewModel.ScreenEvent.DeliveryCannotBeSaved -> {
                    Toast.makeText(context, event.ex.message, Toast.LENGTH_LONG).show()
                }
                NewDeliveryScreenViewModel.ScreenEvent.DeliverySaved -> {
                    navController.popBackStack()
                    Toast.makeText(context, "Delivery saved", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

Scaffold (modifier = Modifier
    .fillMaxHeight()
    .padding(8.dp)
    .fillMaxWidth()
    .padding(top = 20.dp),
    floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.save()
            navController.navigate(Screen.Deliveries.route)
            }) {
            Icon(Icons.Filled.Save, contentDescription = "Add")
        }
    }

            ) {
        innerPadding ->
    Column (   modifier = Modifier
        .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),){
        Text(text = "Delivery", style = MaterialTheme.typography.titleLarge,modifier = Modifier.align(alignment =Alignment.CenterHorizontally))
        InventoryItemCard(
            element = Constants.ELEMENTS[0],
            slider =uiState.value.slider1, quantity = uiState.value.trader.iaspyx, symbol = R.drawable.i, update = {viewModel.update("element",0f)})
        InventoryItemCard(element=Constants.ELEMENTS[1],slider=uiState.value.slider2, quantity = uiState.value.trader.smiathil, symbol = R.drawable.sm, update = {viewModel.update("",0f)})
        InventoryItemCard(element=Constants.ELEMENTS[2],slider=uiState.value.slider3, quantity = uiState.value.trader.jasmalt, symbol = R.drawable.ja, update = {viewModel.update("",0f)})
        InventoryItemCard(element=Constants.ELEMENTS[3], slider=uiState.value.slider4,quantity = uiState.value.trader.vethyx, symbol = R.drawable.ve, update = {viewModel.update("",0f)})
        InventoryItemCard(element=Constants.ELEMENTS[4],slider=uiState.value.slider5, quantity = uiState.value.trader.blierium, symbol = R.drawable.b, update = {viewModel.update("",0f)})

    }


}

    }










