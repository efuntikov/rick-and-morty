package com.efuntikov.rickandmorty

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}