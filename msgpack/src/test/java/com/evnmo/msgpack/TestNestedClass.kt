package com.evnmo.msgpack

import org.junit.Test

class TestNestedClass : BaseTest() {

    @Test
    fun testNestedClass() {
        val original = NestedClass(
            innerClass = InnerClass(
                anEnum = TestEnum.Enum2,
                double = 9.9
            )
        )
        runTest(original, NestedClass::class)
    }

    @Test
    fun testNullableNestedClassWithoutNulls() {
        val original = NullableNestedClass(
            innerClass = InnerClass(
                anEnum = TestEnum.Enum2,
                double = 9.9
            )
        )
        runTest(original, NullableNestedClass::class)
    }

    @Test
    fun testNullableNestedClassWithNulls() {
        val original = NullableNestedClass(
            innerClass = null
        )
        runTest(original, NullableNestedClass::class)
    }
}