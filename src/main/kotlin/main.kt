
// Functional implementation
/**
 *
 */

// exposes kotlin file as static java class
// needed as kotlin has no concept of static classes, and otherwise dokka/javadoc doesn't work
@file:JvmName("WordCount")

package org.devon

import java.io.File
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    val map: MutableMap<String,Int> = mutableMapOf()
    val path = args.firstOrNull()

    // No path input, use standard input
    if (path == null){
        var line = readLine()
        while (line != null && line.isNotEmpty()){
            map.countItems(parseCsvLine(line))
            line = readLine()
        }
    }
    // accept input path and read file
    else {
        try {
            File(path).forEachLine(Charsets.UTF_8) { line ->
                map.countItems(parseCsvLine(line))
            }
        }
        // print error if file doesn't exist
        catch(e: FileNotFoundException){
            println("File $path not found.")
            return
        }
    }

    // print result
    println(map.toCsv())
}

/**
 *  Splits a comma separated string into a list of strings.
 *  Whitespace elements are removed.
 *
 *  @property line The line of text to split
 *  @return A list of the comma separated strings.
 */
fun parseCsvLine(line: String): List<String>{
    return line.split(',').filter { it.isNotBlank() }
}

// Static extension methods https://kotlinlang.org/docs/reference/extensions.html
/**
 * Maps a List<T> onto a MutableMap<T, Int> to cunt instances of identical items.
 *
 * @param T The key type of the MutableMap and the type of the List.
 * @property list The list to map.
 */
fun <T> MutableMap<T,Int>.countItems(list: List<T>){
    list.forEach {
        this.increment(it)
    }
}

/**
 * Increments an integer value at a given key: T
 *
 * @param T the type of the keys in the MutableMap.
 * @property key the ket which to increment
 */
fun <T> MutableMap<T,Int>.increment(key: T){
    this[key] = this.getOrDefault(key, 0) + 1
}

/**
 * Creates a string from the Map in key-value pairs, one pair per line, comma separated
 *
 * @return The map in CSV like form
 */
fun <K,V> Map<K,V>.toCsv() : String {
    return if (this.keys.isEmpty()){
        ""
    } else{
        this.map { (key, value) -> "${key},${value}${System.lineSeparator()}" }
            .reduce { acc, s -> acc + s }
    }

}