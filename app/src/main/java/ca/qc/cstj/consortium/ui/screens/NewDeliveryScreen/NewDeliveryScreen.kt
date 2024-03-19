

package ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.qc.cstj.consortium.ui.theme.ConsortiumTheme


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
                    Toast.makeText(context, "Note saved", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if(!uiState.value.isError) {
                FloatingActionButton(onClick = {
                    viewModel.save()
                }) {
                    Icon(Icons.Filled.Save, contentDescription = Icons.Filled.Add.name)
                }
            }
        }
    ) { innerPaddings ->
        AddScreenContent(
            note = uiState.value.note,
            isError = uiState.value.isError,
            onUpdateTitle = { newTitle -> viewModel.updateTitle(newTitle) },
            onUpdateContent = { viewModel.updateContent(it) },
            onUpdateColor = { viewModel.updateColor(it) },
            modifier = Modifier.padding(innerPaddings),
        )
    }
}





@Preview
@Composable
fun NewDelScreenPreview(){
    ConsortiumTheme {
        NewDeliveryScreen(rememberNavController())
    }
}


