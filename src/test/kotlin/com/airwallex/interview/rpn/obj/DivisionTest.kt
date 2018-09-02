package com.airwallex.interview.rpn.obj
import org.junit.Test
import org.assertj.core.api.Assertions.*

class DivisionTest{

    private val div = Division()
    @Test fun `test division`(){
        assertThat(div.apply(listOf(3.0,1.0))).isEqualTo(listOf(2.0))
    }

    @Test fun `test division validate`(){
        assertThat(div.validate(listOf(1.0,2.0,3.0))).isTrue()
    }
}