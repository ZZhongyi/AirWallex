package com.airwallex.interview.rpn.obj


import org.junit.Test
import org.assertj.core.api.Assertions.*

class CalculatorTest{

    @Test fun `test example1`() {
        val calc = Calculator()
        calc.calc("5 2")
        assertThat(calc.printCurrentStack()).isEqualTo("5 2")
    }

    @Test fun `test example2`(){
        val calc = Calculator()
        calc.calc("2 sqrt")
        assertThat(calc.printCurrentStack()).isEqualTo("1.4142135623")
        calc.calc("clear 9 sqrt")
        assertThat(calc.printCurrentStack()).isEqualTo("3")
    }

    @Test fun `test example3`(){
        val calc = Calculator()
        calc.calc("5 2 -")
        assertThat(calc.printCurrentStack()).isEqualTo("3")
        calc.calc("3 -")
        assertThat(calc.printCurrentStack()).isEqualTo("0")
        calc.calc("clear")
        assertThat(calc.printCurrentStack()).isEqualTo("")
    }

    @Test fun `test example4`(){
        val calc = Calculator()
        calc.calc("5 4 3 2")
        assertThat(calc.printCurrentStack()).isEqualTo("5 4 3 2")
        calc.calc("undo undo *")
        assertThat(calc.printCurrentStack()).isEqualTo("20")
        calc.calc("5 *")
        assertThat(calc.printCurrentStack()).isEqualTo("100")
        calc.calc("undo")
        assertThat(calc.printCurrentStack()).isEqualTo("20 5")
    }

    @Test fun `test example5`(){
        val calc = Calculator()
        calc.calc("7 12 2 /")
        assertThat(calc.printCurrentStack()).isEqualTo("7 6")
        calc.calc("*")
        assertThat(calc.printCurrentStack()).isEqualTo("42")
        calc.calc("4 /")
        assertThat(calc.printCurrentStack()).isEqualTo("10.5")
    }

    @Test fun `test example6`(){
        val calc = Calculator()
        calc.calc("1 2 3 4 5")
        calc.calc("*")
        assertThat(calc.printCurrentStack()).isEqualTo("1 2 3 20")
        calc.calc("clear 3 4 -")
        assertThat(calc.printCurrentStack()).isEqualTo("-1")
    }

    @Test fun `test example7`(){
        val calc = Calculator()
        calc.calc("1 2 3 4 5")
        calc.calc("* * * *")
        assertThat(calc.printCurrentStack()).isEqualTo("120")
    }

    @Test fun `test example8`(){
        val calc = Calculator()
        calc.calc("1 2 3 * 5 + * * 6 5")
        assertThat(calc.printCurrentStack()).isEqualTo("11")
    }
}