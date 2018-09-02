package com.airwallex.interview.rpn.func

import org.junit.Test
import org.assertj.core.api.Assertions.*

internal class CalculatorKtTest{

    private val emptyStacks = Pair(listOf(Stack()),"")

    @Test fun `test apply parameters`(){
        val stacks = listOf(Stack(listOf(3.0,9.0)))
        val res1 = applyPara(stacks, Operator.ADD, 2)
        assertThat(res1.size).isEqualTo(2)
        assertThat(res1.last()).isEqualTo(Stack(listOf(12.0)))
        val res2 = applyPara(stacks,Operator.SQR, 1)
        assertThat(res2.size).isEqualTo(2)
        assertThat(res2.last()).isEqualTo(Stack(listOf(3.0,3.0)))
    }

    @Test fun `test apply wrong parameters`(){
        val stacks = listOf(Stack(listOf(1.0)))
        assertThatExceptionOfType(Exception::class.java)
                .isThrownBy {  applyPara(stacks, Operator.MUL, 2) }
                .withMessage("operator * (position: 1): insufficient parameters")
        assertThatExceptionOfType(ArrayIndexOutOfBoundsException::class.java)
                .isThrownBy { applyPara(stacks, Operator.DIV,1) }
    }

    @Test fun `test apply undo`(){
        val stacks = listOf(Stack(listOf()), Stack(listOf(1.0)))
        assertThat(applyUndo(stacks).size).isEqualTo(1)
        assertThat(applyUndo(applyUndo(stacks)).size).isEqualTo(1)
        assertThat(applyUndo(applyUndo(applyUndo(stacks))).size).isEqualTo(1)
    }

    @Test fun `test apply clear`(){
        val stacks = listOf(Stack(listOf()), Stack(listOf(1.0)))
        assertThat(applyClear(stacks).last()).isEqualTo(Stack())
    }

    @Test fun `test apply opt`(){
        val stacks = listOf(Stack(listOf()), Stack(listOf(1.0)))
        val (ss, msg) = applyOpt(stacks,Operator.SQR)
        assertThat(ss.size).isEqualTo(3)
        assertThat(ss.last()).isEqualTo(Stack(listOf(1.0)))
        assertThat(msg).isEqualTo("")
    }
    @Test fun `test apply opt exception`(){
        val stacks = listOf(Stack(listOf()), Stack(listOf(1.0)))
        val (ss, msg) = applyOpt(stacks,Operator.DIV)
        assertThat(msg.length).isNotEqualTo(0)
        assertThat(ss.size).isEqualTo(2)
    }

    @Test fun `test example1`(){
        assertThat(calc(emptyStacks,"5 2").first.last()).isEqualTo(Stack(listOf(5.0,2.0)))
    }

    @Test fun `test example2`(){
        val res = calc(emptyStacks, "2 sqrt")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(Math.sqrt(2.0))))
        assertThat(calc(res, "clear 9 sqrt").first.last()).isEqualTo(Stack(listOf(3.0)))
    }

    @Test fun `test example3`(){
        var res = calc(emptyStacks, "5 2 -")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(3.0)))
        res = calc(res, "3 -")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(0.0)))
        res = calc(res, "clear")
        assertThat(res.first.last()).isEqualTo(Stack())
    }

    @Test fun `test example4`(){
        var res = calc(emptyStacks, "5 4 3 2")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(5.0,4.0,3.0,2.0)))
        res = calc(res, "undo undo *")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(20.0)))
        res = calc(res, "5 *")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(100.0)))
        res = calc(res, "undo")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(20.0,5.0)))
    }

    @Test fun `test example5`(){
        var res = calc(emptyStacks, "7 12 2 /")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(7.0,6.0)))
        res = calc(res, "*")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(42.0)))
        res = calc(res, "4 /")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(10.5)))
    }

    @Test fun `test example6`(){
        var res = calc(emptyStacks, "1 2 3 4 5")
        res = calc(res, "*")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(1.0,2.0,3.0,20.0)))
        res = calc(res, "clear 3 4 -")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(-1.0)))
    }

    @Test fun `test example7`(){
        var res = calc(emptyStacks, "1 2 3 4 5")
        res = calc(res, "* * * *")
        assertThat(res.first.last()).isEqualTo(Stack(listOf(120.0)))
    }

    @Test fun `test example8`(){
        var res = calc(emptyStacks, "1 2 3 * 5 + * * 6 5")
        assertThat(res.second.length).isNotEqualTo(0)
        assertThat(res.first.last()).isEqualTo(Stack(listOf(11.0)))
    }
}