package com.airwallex.interview.rpn.func

import org.junit.Test
import org.assertj.core.api.Assertions.*

internal class StackTest {

    @Test
    fun `test stack push and pop`() {
        val s = Stack()
        assertThat(s.size()).isEqualTo(0)
        val s1 = s.push(0.0).push(1.0)
        assertThat(s1.size()).isEqualTo(2)
        val s2 = s1.pop(1)
        assertThat(s2.first.size()).isEqualTo(1)
        assertThat(s2.second).isEqualTo(listOf(1.0).toTypedArray())
    }

    @Test
    fun `test stack pop exception`() {
        val s = Stack().push(1.0)
        assertThatIllegalArgumentException().isThrownBy { s.pop(2) }
    }
}