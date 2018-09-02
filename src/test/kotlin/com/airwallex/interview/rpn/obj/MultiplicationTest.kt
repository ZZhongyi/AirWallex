package com.airwallex.interview.rpn.obj

import org.junit.Test
import org.assertj.core.api.Assertions.*

class MultiplicationTest{

    private val mul = Multiplication()
    @Test fun `test multiplication`(){
        assertThat(mul.apply(listOf(4.0,5.0))).isEqualTo(listOf(20.0))
    }
    @Test fun `test multiplication validate`(){
        assertThat(mul.validate(listOf())).isFalse()
    }
}