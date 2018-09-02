package com.airwallex.interview.rpn.func

// stack data structure
data class Stack(val elements:List<Double> = listOf()) {

    fun push(elem: Double): Stack {
        return Stack(elements + elem)
    }

    fun pop(num: Int): Pair<Stack, Array<Double>> {
        val length = elements.size
        return Pair(Stack(elements.subList(0, length - num)), elements.subList(length - num, length).toTypedArray())
    }

    fun size(): Int {
        return elements.size
    }

    override fun toString(): String {
        return super.toString()
    }

}