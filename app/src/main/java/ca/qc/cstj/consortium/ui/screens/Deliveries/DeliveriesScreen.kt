package ca.qc.cstj.consortium.ui.screens.Deliveries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.ui.composables.DeliveryItemCard
import ca.qc.cstj.consortium.ui.navigation.Screen

@Composable
fun DeliveriesScreen(
    navController: NavHostController,
    viewModel : DeliveriesScreenViewModel = viewModel()

) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier
        .fillMaxHeight()
        .padding(8.dp)
        .fillMaxWidth()
        .padding(top = 20.dp),

        floatingActionButton = {
            FloatingActionButton(onClick = {

                navController.navigate(Screen.NewDelivery.route)
            }) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add))
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        )

        {


            Text(text = stringResource(R.string.welcome_back)+ " ${uiState.value.trader.name}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally))

            LazyColumn (modifier = Modifier.padding(top = 16.dp)){
            items(uiState.value.deliveries){item ->
                DeliveryItemCard(del = item)
            }
        }



        }

    }
}