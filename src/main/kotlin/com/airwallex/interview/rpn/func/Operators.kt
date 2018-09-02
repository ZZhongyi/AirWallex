package com.airwallex.interview.rpn.func

// operators with toString override
enum class Operator{
    ADD
    {
        override fun toString():String{
            return "+"
        }
    },
    SUB{
        override fun toString():String{
            return "-"
        }

    },
    MUL{
        override fun toString():String {

            return "*"
        }
    },
    DIV{
        override fun toString(): String {
            return "/"
        }
    },
    SQR{
        override fun toString(): String {
            return "sqrt"
        }
    },
    CLR{
        override fun toString(): String {
            return "clear"
        }
    },
    UND{
        override fun toString(): String {
            return "undo"
        }
    }

}

fun parse(rawPara:String):Any {
    // parse the input token
    // throw exception on error
    return when (rawPara) {
        "+" -> Operator.ADD
        "-" -> Operator.SUB
        "*" -> Operator.MUL
        "/" -> Operator.DIV
        "sqrt" -> Operator.SQR
        "clear" -> Operator.CLR
        "undo" -> Operator.UND
        else -> rawPara.toDouble()
    }
}

fun getFunc(opt:Operator):(Array<Double>) -> Double {
    // lookup function mapping
    // throw exceptions if key not exist
    return mapOf(Operator.ADD to { it: Array<Double> -> it[0] + it[1] },
            Operator.SUB to { it: Array<Double> -> it[0] - it[1] },
            Operator.MUL to { it: Array<Double> -> it[0] * it[1] },
            Operator.DIV to { it: Array<Double> -> it[0] / it[1] },
            Operator.SQR to { it: Array<Double> -> Math.sqrt(it[0]) }).getValue(opt)
}
