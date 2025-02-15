package com.example.temperatureconversion

import  androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt

class ViewModelForTemperatures: ViewModel()
{
    // state of current temp. This can be toggled with the switch control.
    var isFahrenheit by mutableStateOf( true)
    // calculated result. This will be updated when the calculateConversion function is called.
    var convertedTemperature by mutableStateOf( "")


    fun doSwitchToggle ()
    {
        isFahrenheit = !isFahrenheit
    }

    // return a converted value given an input
    // Return a converted value given an input
    fun calculateConversion(inputValue: String) {
        try {
            val temperature = inputValue.toInt()
            convertedTemperature = if (isFahrenheit) {
                // Convert to Celsius (℃)
                ((temperature - 32) * 5 / 9.0).roundToInt().toString() + "\u2103"
            } else {
                // Convert to Fahrenheit (℉)
                ((temperature * 9 / 5.0) + 32).roundToInt().toString() + "\u2109"
            }
        } catch (e: Exception) {
            // Handle invalid input
            convertedTemperature = "Error"
        }
    }


}