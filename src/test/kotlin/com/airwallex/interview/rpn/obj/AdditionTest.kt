package com.airwallex.interview.rpn.obj


import org.junit.Test
import org.assertj.core.api.Assertions.*

class AdditionTest{

    private val add = Addition()
    @Test fun `test addition`(){
        assertThat(add.apply(listOf(1.0, 2.0))).isEqualTo(listOf(3.0))
    }

    @Test fun `test addition validate`(){
        assertThat(add.validate(listOf())).isFalse()
        assertThat(add.validate(listOf(1.0,2.0))).isTrue()
    }

}