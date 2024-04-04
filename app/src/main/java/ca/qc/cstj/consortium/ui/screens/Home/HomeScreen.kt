package ca.qc.cstj.consortium.ui.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ca.qc.cstj.consortium.R
import ca.qc.cstj.consortium.ui.composables.TraderInventory
import ca.qc.cstj.consortium.ui.navigation.Screen

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
        OutlinedTextField(value = uiState.value.traderNameField , onValueChange ={ newName ->viewModel.updateNameField(newName)} ,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(96.dp)
                .align(Alignment.CenterHorizontally),
            label = { Text(stringResource(R.string.trader_name)) },
            supportingText = {
                if (uiState.value.isError){
                    Text(
                        text = stringResource(R.string.error_please_enter_name),
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            isError = uiState.value.isError,
            trailingIcon = {
                if (uiState.value.isError){
                    Icon(imageVector = Icons.Filled.Error,
                        contentDescription = stringResource(R.string.error_please_enter_name) ,
                        tint = MaterialTheme.colorScheme.error)
                }
            }



            )

        Row (modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp)
            .align(Alignment.CenterHorizontally) ) {
            TraderInventory(uiState.value.trader)
        }
        Column (modifier = Modifier
            .padding(32.dp)
            .align(Alignment.CenterHorizontally)){
            Button(onClick = { if (viewModel.open()){

                navController.navigate(Screen.Deliveries.route)
            } }, modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(8.dp)) {
                Image(colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),painter = painterResource(id = R.drawable.baseline_door_front_24) ,
                    contentDescription = stringResource(R.string.open) )
                Text(text = stringResource(R.string.open), modifier = Modifier.padding(start = 8.dp))
            }

            Button(onClick = { viewModel.load() }, modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(8.dp)) {
                Image(colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),painter = painterResource(id = R.drawable.baseline_sync_24) ,
                    contentDescription = stringResource(R.string.load) )
                Text(text = stringResource(R.string.load), modifier = Modifier.padding(start = 8.dp))
            }

            Button(onClick = { viewModel.upload()}, modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(8.dp)) {
                Image(colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),painter = painterResource(id = R.drawable.baseline_cloud_upload_24) ,
                    contentDescription = stringResource(R.string.upload) )
                Text(text = stringResource(R.string.upload) , modifier = Modifier.padding(start = 8.dp))
            }
            Text(text = "Olivier Paul-Hus - 2024", modifier = Modifier.align(Alignment.CenterHorizontally), color = MaterialTheme.colorScheme.secondary)
        }


    }





}