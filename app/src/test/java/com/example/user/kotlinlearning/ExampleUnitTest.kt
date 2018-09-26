package com.example.user.kotlinlearning

import org.junit.Assert.assertEquals
import org.junit.Test

data class Mem(val name: String, val age: Int)

class ExampleUnitTest {

    private val digits = listOf(1, 2, 3, 4)
    private val memes = listOf(
            Mem("Garold", 73),
            Mem("Doge", 15),
            Mem("GabeTheDog", 12))

    @Test
    fun testFilterOperation() {
        println("GETZ.ExampleUnitTest.testFilter ---> ${digits.filter { it % 2 == 0 }}")
        println("GETZ.ExampleUnitTest.testFilter ---> ${memes.filter { it.age >= 30 }}")
        assertEquals(1, 1)
    }

    @Test
    fun testMapOperation() {
        println("GETZ.ExampleUnitTest.testMap ---> ${digits.map { it * it }}")
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.map { it.name }}")
        //After refactoring (using ref to class member)
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.map { Mem::name }}")
        //Filter and Map can be grouped.
        println("GETZ.ExampleUnitTest.testMap ---> ${memes.filter { it.age > 20 }.map { it.age }}")
        assertEquals(1, 1)
    }

    @Test
    fun testMapAndFilterForMapDataStructure() {
        val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two", 3 to "three")
        println("GETZ.ExampleUnitTest.testMapAndFilterForMapDataStructure ---> " +
                "${numbers.mapValues { it.value.toUpperCase() }}")
        println("GETZ.ExampleUnitTest.testMapAndFilterForMapDataStructure ---> " +
                "${numbers.filterKeys { it >= 2 }}}")
    }
}