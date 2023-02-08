package com.example.guidedaechelin.main.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.get
import com.example.guidedaechelin.databinding.DialogCalendarBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class CalendarDialog ( private val context : Context) {

    private lateinit var binding : DialogCalendarBinding
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClicklistener(listener : OnDialogClickListener){
        onClickListener = listener
    }

    fun showDialog(){

        binding = DialogCalendarBinding.inflate(LayoutInflater.from(context))

        dialog.setContentView(binding.root)
        dialog.show()

        context.dialogResize(dialog,0.8F,0.6F)
        dialog.getWindow()!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))

        binding.calendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->

            Log.d("캘린더","${i2+1}.${i3}.${i}}")
//            onClickListener.onClicked( longToCalendar(binding.calendarView.date ))

            val dateString = "${i2+1}.${i3}.${i}"

            val formatter = SimpleDateFormat("MM.dd.yyyy")
            val date = formatter.parse(dateString)

            val cal = Calendar.getInstance()
            cal.time=date

            onClickListener.onClicked(cal)

        }

    }

    interface OnDialogClickListener{
        fun onClicked(date : Calendar)
    }

    fun longToCalendar(time: Long): Calendar {
        var c: Calendar? = null
        if (time != null) {
            c = Calendar.getInstance()
            c.timeInMillis = time
        }
        return c!!
    }

    fun Context.dialogResize(dialog: Dialog, width: Float, height: Float){
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog.window

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()

            window?.setLayout(x, y)

        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog.window
            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }
    }
}