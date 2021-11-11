package com.efuntikov.rickandmorty.android.fragment

import androidx.fragment.app.Fragment
import com.efuntikov.rickandmorty.ViewModelBinding

open class RMBaseFragment : Fragment() {
    protected val binding = ViewModelBinding()

    protected open fun binding() {}
}