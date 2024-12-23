package com.example.taskcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstInputTimeET:EditText
    private lateinit var secondInputTimeET:EditText

    private lateinit var textResultTV:TextView

    private lateinit var buttonResultBTN:Button
    private lateinit var buttonClearBTN:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firstInputTimeET = findViewById(R.id.firstInputTimeET)
        secondInputTimeET = findViewById(R.id.secondInputTimeET)
        textResultTV = findViewById(R.id.textResultTV)
        buttonResultBTN = findViewById(R.id.buttonResultBTN)
        buttonClearBTN = findViewById(R.id.buttonClearBTN)
    }
    fun onClickResult(view:View){
        if(firstInputTimeET.text.isEmpty() || secondInputTimeET.text.isEmpty()) return
        var time1:Int = stringToTime(firstInputTimeET.text.toString()) ?: return
        var time2:Int = stringToTime(secondInputTimeET.text.toString()) ?: return
        val time = time1+time2
        textResultTV.text = "${(time/3600)}h${(time%3600)/60}m${time%60}"

    }
    fun onClickClear(view:View){
        firstInputTimeET.text.clear()
        secondInputTimeET.text.clear()
    }
    @SuppressLint("SetTextI18n")
    fun stringToTime(text:String):Int?{
        var time = 0
        var hoursInt:Int? = 0
        var minutsInt:Int? = 0
        var secundsInt:Int? = 0
        if (text.contains("h")) hoursInt = text.substringBefore('h').toIntOrNull()
        if (text.contains("m")) minutsInt = text.substringAfter('h').substringBefore('m').toIntOrNull()
        if (text.contains("s")) secundsInt = text.substringAfter('m').substringBefore('s').toIntOrNull()
        if(hoursInt==null || minutsInt == null || secundsInt == null){
            textResultTV.text = "Ошибочка!"
            return null
        }
        return hoursInt*3600+minutsInt*60+secundsInt
    }
}