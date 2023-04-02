package com.halac123b.birthcalculator

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dateText: TextView? = null
    private var minuteText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDatePicker = findViewById<Button>(R.id.btnPickDate)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
        dateText = findViewById(R.id.selectedDateText)
        minuteText = findViewById(R.id.minuteText)
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialogue = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    _, year, month, dayOfMonth ->
                    Toast.makeText(this, "Date picker works", Toast.LENGTH_LONG).show()
                val selectedDate: String = "$dayOfMonth/${month + 1}/$year"
                dateText?.text = selectedDate

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                // Convert dữ liệu từ string thành Date Object
                val theDate = dateFormat.parse(selectedDate)
                // Đổi sang phút giá trị ngày được chọn
                val selectedDateInMinute = theDate.time / 60000
                // Tính thời gian hiện tại
                val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                val currentDateInMinute = currentDate.time / 60000

                val minuteResult = currentDateInMinute - selectedDateInMinute
                minuteText?.text = minuteResult.toString()
            },
        year, month, day)
        datePickerDialogue.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialogue.show()
    }
}