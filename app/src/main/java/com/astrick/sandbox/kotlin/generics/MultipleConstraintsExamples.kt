package com.astrick.sandbox.kotlin.generics

fun <T> ensureTrailingPeriod(seq: T) where T: CharSequence, T: Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

interface Printable {
    fun print(): String
}

interface Savable {
    fun save(): Boolean
}

class Document : Printable, Savable {
    override fun print() = "Printing Document..."
    override fun save() = true
}

fun main() {
    val doc = Document()
    process(doc)
}

fun <T> process(item: T) where T : Printable, T : Savable {
    println(item.print())
    if (item.save()) println("Item saved successfully.")
}
