package com.efuntikov.rickandmorty.repository

interface Repository<T> {
    suspend fun get(): T
}