package com.airwallex.interview.rpn.obj


class Calculator(var current:List<Double> = listOf(), var history: MutableList<List<Double>> = mutableListOf()) {

    fun calc(exp: String) {
        val tokens = exp.split(" ")
        loop@ for (token in tokens) {
            val next = parse(token)
            if (next == null) {
                println("Unknown token: $token (ignored)")
            }
            when (next) {
                is IOperator -> {
                    current = if (next is Undo) {
                        history.removeAt(history.size - 1)
                    } else {
                        if (next.validate(current)) {
                            history.add(current)
                            next.apply(current)
                        } else {
                            println("operator ${next.token} (position: ${history.size}): insufficient parameters")
                            break@loop
                        }
                    }

                }
                is Double -> {
                    history.add(current)
                    current += next
                }
            }
        }
    }

    private fun parse(token: String): Any? {
        return IOperator.fromString(token) ?: Number.fromString(token)
    }

    fun printCurrentStack():String{
        return current.map { it -> Number.formatString(it) }.joinToString(" ")
    }

}

fun main(args:Array<String>) {

    // init empty stacks
    val calculator = Calculator()
    while (true) {
        // read input
        val input = readLine()!!
        if (input == ":q") break
        // calculate input with current stacks
        calculator.calc(input)
        println(calculator.printCurrentStack())
    }
    println("bye!")

}



