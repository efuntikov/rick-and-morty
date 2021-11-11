package com.efuntikov.rickandmorty.android

import androidx.appcompat.app.AppCompatActivity
import com.efuntikov.rickandmorty.ViewModelBinding

open class RMBaseActivity : AppCompatActivity() {
    protected val binding = ViewModelBinding()

    protected open fun binding() {}
}