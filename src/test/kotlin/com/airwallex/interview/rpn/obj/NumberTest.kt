package com.airwallex.interview.rpn.obj


import org.junit.Test
import org.assertj.core.api.Assertions.*

class NumberTest{

    @Test fun `test parse number`(){
        assertThat(Number.fromString("1.3")).isEqualTo(1.3)
        assertThat(Number.fromString("not")).isNull()
    }

    @Test fun `test format number`(){
        assertThat(Number.formatString(0.0)).isEqualTo("0")
        assertThat(Number.formatString(20.0)).isEqualTo("20")
        assertThat(Number.formatString(1.0)).isEqualTo("1")
        assertThat(Number.formatString(1.3)).isEqualTo("1.3")
        assertThat(Number.formatString(0.123456789112345)).isEqualTo("0.1234567891")
    }
}