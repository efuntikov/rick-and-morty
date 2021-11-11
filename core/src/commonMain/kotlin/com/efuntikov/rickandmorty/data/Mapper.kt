package com.efuntikov.rickandmorty.data

interface Mapper<in T, out E> {
    fun transform(response: T): E
}