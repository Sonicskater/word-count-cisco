import org.devon.countItems
import org.devon.increment
import org.devon.parseCsvLine
import org.devon.toCsv
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

//
internal class ParseCsvLine {

    @Test
    fun `should parse a comma seperated string of words to a list`() {
        assertEquals(listOf("red", "blue","green"),parseCsvLine("red,blue,green"))
    }
    @Test
    fun `should ignore trailing commas`() {
        assertEquals(listOf("red", "blue","green"),parseCsvLine("red,blue,green,"))
    }

    @Test
    fun `should ignore entries containing only whitespace`() {
        assertEquals(listOf("red", "blue","green"),parseCsvLine("red,blue, ,green"))
    }

}

internal class CountItems {
    @Test
    fun `should count a list of strings into a map`(){
        val x : MutableMap<String, Int> = mutableMapOf()
        x.countItems(listOf("a","b","b","c"))
        assertEquals(1,x["a"])
        assertEquals(2,x["b"])
        assertEquals(1,x["c"])
        assertEquals(null,x["d"])
    }

    @Test
    fun `should count a list of integers into a map`(){
        val x : MutableMap<Int, Int> = mutableMapOf()
        x.countItems(listOf(1,15,15,-1234))
        assertEquals(1,x[1])
        assertEquals(2,x[15])
        assertEquals(1,x[-1234])
        assertEquals(null,x[0])
    }
}

internal class Increment{
    @Test
    fun `should increment the value found in the map with a String key`(){
        val x : MutableMap<String, Int> = mutableMapOf("a" to 1)
        assertEquals(1, x["a"])
        x.increment("a")
        assertEquals(2, x["a"])
    }

    @Test
    fun `should add the new value to the map with a String key`(){
        val x : MutableMap<String, Int> = mutableMapOf()
        assertEquals(null, x["a"])
        x.increment("a")
        assertEquals(1, x["a"])
    }

    @Test
    fun `should not increment any other values with a String key`(){
        val x : MutableMap<String, Int> = mutableMapOf("a" to 0)
        assertEquals(null, x["b"])
        x.increment("b")
        assertEquals(1, x["b"])
        assertEquals(0, x["a"])
    }

    @Test
    fun `should increment the value found in the map with an Int key`(){
        val x : MutableMap<Int, Int> = mutableMapOf(2 to 1)
        assertEquals(1, x[2])
        x.increment(2)
        assertEquals(2, x[2])
    }

    @Test
    fun `should add the new value to the map with an Int key`(){
        val x : MutableMap<Int, Int> = mutableMapOf()
        assertEquals(null, x[2])
        x.increment(2)
        assertEquals(1, x[2])
    }

    @Test
    fun `should not increment any other values with an Int key`(){
        val x : MutableMap<Int, Int> = mutableMapOf(1 to 0)
        assertEquals(null, x[2])
        x.increment(2)
        assertEquals(1, x[2])
        assertEquals(0, x[1])
    }
}

internal class ToCsv(){

    @Test
    fun `should return a csv like form of a map of String,Int`(){
        val expected = "red,1${System.lineSeparator()}green,2${System.lineSeparator()}"
        val x = mapOf("red" to 1, "green" to 2)
        assertEquals(expected, x.toCsv())
    }

    @Test
    fun `should return a csv like form of a map of String,String`(){
        val expected = "red,blue${System.lineSeparator()}green,three${System.lineSeparator()}"
        val x = mapOf("red" to "blue", "green" to "three")
        assertEquals(expected, x.toCsv())
    }

    @Test
    fun `should return a csv like form of a map of Int,String`(){
        val expected = "1,one${System.lineSeparator()}2,two${System.lineSeparator()}"
        val x = mapOf(1 to "one", 2 to "two")
        assertEquals(expected, x.toCsv())
    }

    @Test
    fun `should return a csv like form of a map of String,Any`(){
        val expected = "red,one${System.lineSeparator()}green,2${System.lineSeparator()}"
        val x = mapOf("red" to "one", "green" to 2)
        assertEquals(expected, x.toCsv())
    }
}