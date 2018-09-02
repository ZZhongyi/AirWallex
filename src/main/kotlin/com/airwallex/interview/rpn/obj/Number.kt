package com.airwallex.interview.rpn.obj

import kotlin.math.roundToInt

object Number{

    fun fromString(token:String):Double?{
        return token.toDoubleOrNull()
    }

    fun formatString(number:Double):String{
        return if (number == 0.0) "0" else String.format("%.10f",number).trimEnd('0').trim('.')

    }
}