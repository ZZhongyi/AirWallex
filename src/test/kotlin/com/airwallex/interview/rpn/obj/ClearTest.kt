package com.airwallex.interview.rpn.obj


import org.junit.Test
import org.assertj.core.api.Assertions.*

class ClearTest{

    @Test fun `test clear`(){
        assertThat(Clear().apply(listOf())).isEqualTo(listOf<Double>())
    }
}