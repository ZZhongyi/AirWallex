package com.airwallex.interview.rpn.func

// stack data structure
data class Stack(val elements:List<Double> = listOf()) {

    fun push(elem: Double): Stack {
        return Stack(elements + elem)
    }

    fun pop(num: Int): Pair<Stack, Array<Double>> {
        return Pair(Stack(elements.dropLast(num)), elements.takeLast(num).toTypedArray())
    }

    fun size(): Int {
        return elements.size
    }

    override fun toString(): String {
        return elements.joinToString(" ") { it -> if (it == 0.0) "0" else String.format("%.10f", it).trimEnd('0','.') }
    }

}