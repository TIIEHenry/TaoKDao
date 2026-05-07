package taokdao.main

import java.text.SimpleDateFormat
import java.util.*


fun main() {
    Locale.setDefault(Locale.CHINA)
    val calendar: Calendar = Calendar.getInstance()
    val date = SimpleDateFormat("YYYY/MM/dd").format(calendar.time)
    val time = SimpleDateFormat("HH:mm").format(calendar.time)
    val year = SimpleDateFormat("YYYY").format(calendar.time)
    val month = SimpleDateFormat("MM").format(calendar.time)
    val monthNameShort = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
    val monthNameFull = SimpleDateFormat("MMMMMM", Locale.getDefault()).format(calendar.time)
//    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val day = SimpleDateFormat("dd").format(calendar.time)
    val dayNameShort = SimpleDateFormat("E").format(calendar.time)
    val dayNameFull = SimpleDateFormat("EE").format(calendar.time)
//    SimpleDateFormat("DD").parse(dayNameShort.toString())
//    val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    val hour = SimpleDateFormat("HH").format(calendar.time)
//    val minute: Int = calendar.get(Calendar.MINUTE)
    val minute = SimpleDateFormat("mm").format(calendar.time)
    println(date)
    println(time)
    println(year)
    println(month)
    println(monthNameShort)
    println(monthNameFull)
    println(day)
    println(dayNameShort)
    println(dayNameFull)
    println(hour)
    println(minute)

}
