package com.airwallex.interview.rpn.func

import org.junit.Test
import org.assertj.core.api.Assertions.*
import java.lang.NumberFormatException

internal class OperatorTest {

    @Test
    fun `test operator toString`() {
        assertThat(Operator.ADD.toString()).isEqualTo("+")
        assertThat(Operator.SUB.toString()).isEqualTo("-")
        assertThat(Operator.MUL.toString()).isEqualTo("*")
        assertThat(Operator.DIV.toString()).isEqualTo("/")
        assertThat(Operator.CLR.toString()).isEqualTo("clear")
        assertThat(Operator.SQR.toString()).isEqualTo("sqrt")
        assertThat(Operator.UND.toString()).isEqualTo("undo")
    }

    @Test
    fun `test parse params from string`() {
        assertThat(parse("+")).isEqualTo(Operator.ADD)
        assertThat(parse("-")).isEqualTo(Operator.SUB)
        assertThat(parse("*")).isEqualTo(Operator.MUL)
        assertThat(parse("/")).isEqualTo(Operator.DIV)
        assertThat(parse("clear")).isEqualTo(Operator.CLR)
        assertThat(parse("sqrt")).isEqualTo(Operator.SQR)
        assertThat(parse("undo")).isEqualTo(Operator.UND)
        assertThat(parse("1.234")).isEqualTo(1.234)
    }

    @Test
    fun `test parse params exception`() {
        assertThatExceptionOfType(NumberFormatException::class.java).isThrownBy { parse("something") }
    }

    @Test
    fun `test operator func`() {
        assertThat(getFunc(Operator.ADD).invoke(arrayOf(1.2, 2.3))).isEqualTo(3.5)
        assertThat(getFunc(Operator.SUB).invoke(arrayOf(3.0, 4.0))).isEqualTo(-1.0)
        assertThat(getFunc(Operator.MUL).invoke(arrayOf(3.0, 3.0))).isEqualTo(9.0)
        assertThat(getFunc(Operator.DIV).invoke(arrayOf(9.0, 3.0))).isEqualTo(3.0)
        assertThat(getFunc(Operator.SQR).invoke(arrayOf(9.0))).isEqualTo(3.0)
    }

    @Test
    fun `test operator func exception`() {
        assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy { getFunc(Operator.UND) }
    }

}