package ca.qc.cstj.consortium.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.ui.composables.TraderInventory

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel : HomeScreenViewModel = viewModel(),
){
    //Va changer à chacune des modifications d'état déclenchant une recomposition
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()



    Column {
        Image(painter = painterResource(id = R.drawable.consortium), contentDescription ="Logo" , modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxHeight(0.4f))
        OutlinedTextField(value = uiState.value.trader.name , onValueChange ={} ,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text("Trader Name") }

            )
        Row (modifier = Modifier.fillMaxWidth(0.9f).padding(top = 16.dp).align(Alignment.CenterHorizontally) ) {
            TraderInventory(uiState.value.trader)
        }

    }





}