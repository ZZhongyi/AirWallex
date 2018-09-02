package com.airwallex.interview.rpn.obj

interface IOperator{
    val token: String
    fun apply(stack:List<Double>):List<Double>
    fun validate(stack:List<Double>):Boolean

    companion object {
        private val supportedOperators = listOf(
                Addition(),
                Substraction(),
                Multiplication(),
                Division(),
                SquareRoot(),
                Clear(),
                Undo()
        )


        fun fromString(token:String):IOperator?{
            return supportedOperators.find { it -> it.token == token }
        }
    }
}

class Addition :IOperator {

    override val token = "+"

    override fun validate(stack: List<Double>): Boolean {
        return stack.size >= 2
    }

    override fun apply(stack: List<Double>): List<Double> {
        val (a, b) = stack.takeLast(2)
        return stack.dropLast(2) + (a + b)
    }

}

class Substraction:IOperator {
    override val token: String = "-"

    override fun apply(stack: List<Double>): List<Double> {
        val (a, b) = stack.takeLast(2)
        return stack.dropLast(2) + (a - b)
    }

    override fun validate(stack: List<Double>): Boolean {
        return stack.size >= 2
    }

}

class Multiplication:IOperator{
    override val token: String = "*"

    override fun apply(stack: List<Double>): List<Double> {
        val (a, b) = stack.takeLast(2)
        return stack.dropLast(2) + (a * b)
    }

    override fun validate(stack: List<Double>): Boolean {
        return stack.size >= 2
    }
}

class Division:IOperator {
    override val token: String = "/"

    override fun apply(stack: List<Double>): List<Double> {
        val (a, b) = stack.takeLast(2)
        return stack.dropLast(2) + (a / b)
    }

    override fun validate(stack: List<Double>): Boolean {
        return stack.size >= 2
    }

}

class SquareRoot:IOperator {
    override val token: String = "sqrt"

    override fun apply(stack: List<Double>): List<Double> {
        return stack.dropLast(1) + Math.sqrt(stack.last())
    }

    override fun validate(stack: List<Double>): Boolean {
        return stack.isNotEmpty()
    }

}

class Clear:IOperator{
    override val token: String = "clear"

    override fun apply(stack: List<Double>): List<Double> = listOf()

    override fun validate(stack: List<Double>): Boolean = true
}

class Undo:IOperator{
    override val token: String = "undo"

    override fun apply(stack: List<Double>): List<Double> {
        throw Exception("Undo doesn't have apply method")
    }
    override fun validate(stack: List<Double>): Boolean = true

}
