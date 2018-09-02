package com.airwallex.interview.rpn.obj

import org.junit.Test
import org.assertj.core.api.Assertions.*

class SubstractionTest{

    val sub = Substraction()

    @Test fun `test sub apply`(){
        assertThat(sub.apply(listOf(2.0,3.0))).isEqualTo(listOf(-1.0))
    }

    @Test fun `test sub vaidate`(){
        assertThat(sub.validate(listOf())).isFalse()
    }


}