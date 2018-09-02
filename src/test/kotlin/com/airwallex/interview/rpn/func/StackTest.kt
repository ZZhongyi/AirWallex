package com.airwallex.interview.rpn.func

import org.junit.Test
import org.assertj.core.api.Assertions.*

internal class StackTest {

    @Test
    fun `test stack push and pop`() {
        val s = Stack()
        assertThat(s.size()).isEqualTo(0)
        assertThat(s.toString()).isEqualTo("")
        val s1 = s.push(0.0).push(1.0)
        assertThat(s1.size()).isEqualTo(2)
        assertThat(s1.toString()).isEqualTo("0 1")
        val s2 = s1.pop(1)
        assertThat(s2.first.size()).isEqualTo(1)
        assertThat(s2.first.toString()).isEqualTo("0")
        assertThat(s2.second).isEqualTo(listOf(1.0).toTypedArray())
    }

}