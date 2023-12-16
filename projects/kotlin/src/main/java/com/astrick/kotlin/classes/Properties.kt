package com.astrick.kotlin.classes

// https://kotlinlang.org/docs/properties.html

data class SomeProperty(
    var someThing: String
)

data class Field(val field: String)
class Playground() {
    private var _someProperty = SomeProperty("")
    var field = Field("")
        set(value) {
            field = value
        }
    
    
    val customerGetter: SomeProperty
        get() {
            println("Using custom getter for SomeProperty")
            return _someProperty // Creates a new Holder instance with the same value
        }
    val directReference = _someProperty
    
    fun changeSomeThing(newThing: String) {
        _someProperty.someThing = newThing
    }
}

fun main() {
    val playground = Playground()
    
    val customGetter1 = playground.customerGetter
    val customGetter2 = playground.customerGetter
    
    val directReference = playground.directReference
    
    customGetter1.someThing = "initial thing"
    
    println("customGetter1: ${customGetter1.someThing}")
    println("customGetter2: ${customGetter2.someThing}")
    println("directReference: ${directReference.someThing}")
    
    playground.changeSomeThing("new thing")
    
    println("customGetterHolder1.number: ${customGetter1.someThing}")
    println("customGetterHolder2.number: ${customGetter2.someThing}")
    println("directReference: ${directReference.someThing}")
    
    print("${customGetter1 === directReference}")
    
    val newPlayGround = playground
    newPlayGround.changeSomeThing("What 1?")
    playground.changeSomeThing("What?")
    println("newPlayGround: ${newPlayGround.directReference.someThing}")
    
}