package com.example.temperatureconversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temperatureconversion.ui.theme.TemperatureConversionTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Switch
import androidx.compose.material3.OutlinedTextField


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConversionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Greeting("Android")
                    Mypp()
                }
            }
        }
    }
}

@Composable
fun Mypp() {
    // Create an instance of ViewModel
    val viewModel: ViewModelForTemperatures = viewModel()

    // Pass each part of the ViewModel properties and events as parameters
    // to the next level down. Google doesn't recommend passing the ViewModel itself.
    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.convertedTemperature,
        convertTemp = { input -> viewModel.calculateConversion(input) }, // Pass the input
        doToggle = { viewModel.doSwitchToggle() }
    )
}

@Composable
fun MainScreen(
    isFahrenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit, // Accept input parameter
    doToggle: () -> Unit

) {

    var inputTextState by remember { mutableStateOf("") }
    val OnTextChange= {text : String -> inputTextState = text}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Title
        Text(
            text = "Temperature Converter",
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        // Card for inputs
        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                // Switch to toggle Fahrenheit/Celsius
                Switch(
                    checked = isFahrenheit,
                    onCheckedChange = { doToggle() }
                )

                // Text field for user input
                OutlinedTextField(
                    value = inputTextState,
                    onValueChange =  OnTextChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Enter Degrees") },
                    modifier = Modifier.padding(12.dp),
                    textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold) ,
                    trailingIcon = {
                        if (isFahrenheit) {
                            Text(
                                text = "\u2109", // Fahrenheit symbol
                                style = MaterialTheme.typography.bodyMedium
                            )
                        } else {
                            Text(
                                text = "\u2103", // Celsius symbol
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                )
            }
        }

        // Result text
        Text(
            text = result,
            modifier = Modifier.padding(18.dp),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(weight = 5)
        )

        // Button to trigger conversion
        Button(onClick = { convertTemp(inputTextState) },
            modifier =Modifier.padding(10.dp) ) {
            if (isFahrenheit)
            {
                Text(text = "Convert to C")
            }
            else
            {
                Text(text = "Convert to F")
            }

        }
    }
}

fun Switch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    // TODO: Implement your Switch
}


fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,  // Updated parameter type
    keyboardOptions: KeyboardOptions, // Update type if necessary
    label: @Composable () -> Unit,
    modifier: Modifier,
    textStyle: TextStyle,             // Update type if necessary
    trailingIcon: @Composable () -> Unit
) {
    // TODO: Implement your OutlinedTextField
}

