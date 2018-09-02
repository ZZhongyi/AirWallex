package com.airwallex.interview.rpn.obj

import org.junit.Test
import org.assertj.core.api.Assertions.*

class SquareRootTest{

    val sqrt = SquareRoot()

    @Test fun `test square root`(){
        assertThat(sqrt.apply(listOf(9.0))).isEqualTo(listOf(3.0))
    }

    @Test fun `test square root validate`(){
        assertThat(sqrt.validate(listOf())).isFalse()
    }
}