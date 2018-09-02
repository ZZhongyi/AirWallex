package com.airwallex.interview.rpn.func

fun main(args:Array<String>) {

    // init empty stacks
    var stacks = Pair(listOf(Stack()), "")
    while (true) {
        // read input
        val input = readLine()!!
        if (input == ":q") break
        // calculate input with current stacks
        stacks = calc(stacks, input)

        if (stacks.second != "")
        // output error message if any
            println(stacks.second)
        // output current stack status
        println(stacks.first.last())

        // reset error message
        stacks = Pair(stacks.first, "")
    }
    println("bye!")

}

fun calc(prev: Pair<List<Stack>,String>, exp:String): Pair<List<Stack>,String> {
    // split input and fold tokens
    return exp.split(" ").fold(prev) { s, a -> calcOne(s, a) }
}


fun calcOne(prev:Pair<List<Stack>,String>, next:String):Pair<List<Stack>,String> {
    val token = parse(next)
    val stacks = prev.first
    return if (prev.second != "")
        prev
    else
        when (token) {
            is Double -> Pair(stacks + stacks.last().push(token), "")
            is Operator -> applyOpt(stacks, token)
            else -> {
                println("Unknown: $next (ignored)");Pair(stacks, "")
            }
        }
}

fun applyOpt(stacks: List<Stack>, opt:Operator):Pair<List<Stack>,String> {
    return try {
        val res = when (opt) {
            Operator.ADD, Operator.SUB, Operator.MUL, Operator.DIV -> applyPara(stacks, opt, 2)
            Operator.SQR -> applyPara(stacks, opt, 1)
            Operator.UND -> applyUndo(stacks)
            Operator.CLR -> applyClear(stacks)

        }
        Pair(res, "")
    } catch (e: Exception) {
        Pair(stacks, e.message!!)
    }
}

fun applyClear(stacks: List<Stack>):List<Stack> {
    return stacks + Stack(listOf())

}

fun applyUndo(stacks: List<Stack>): List<Stack> {

    return if (stacks.size == 1)
        stacks
    else
        stacks.subList(0, stacks.size - 1)
}

fun applyPara(stacks: List<Stack>, opt: Operator, paraNum:Int):List<Stack> {
    if (stacks.last().size() < paraNum) {
        throw Exception("operator $opt (position: ${stacks.size}): insufficient parameters")
    }
    else {
        val (first, second) = stacks.last().pop(paraNum)
        return stacks + first.push(getFunc(opt).invoke(second))
    }
}
