package com.example.androiddigitalclock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

class MainActivityViewModel : ViewModel() {

    val secondsRightDisplayManager = DigitDisplayManager()
    val secondsLeftDisplayManager = DigitDisplayManager()
    val hourRightDisplayManager = DigitDisplayManager()
    val hourLeftDisplayManager = DigitDisplayManager()


    @RequiresApi(Build.VERSION_CODES.O)
    fun showLiveTime() {
        viewModelScope.launch {

            while (true) {
                val currentTime = LocalTime.now()
                var hours = currentTime.hour
                val min = currentTime.minute

                hours = if (hours > 12) hours - 12 else if (hours == 0) 12 else hours

                hourRightDisplayManager.onNewDigit(hours % 10)
                hourLeftDisplayManager.onNewDigit(hours / 10)

                secondsRightDisplayManager.onNewDigit(min % 10)
                secondsLeftDisplayManager.onNewDigit(min / 10)
                delay(6000)
            }
        }
    }
}