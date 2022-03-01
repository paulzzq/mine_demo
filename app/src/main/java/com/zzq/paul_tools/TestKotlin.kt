package com.zzq.paul_tools

import android.util.Log

class TestKotlin {
    companion object {
        @JvmStatic
        fun main(args: Array<String>){
//        Log.e(hasPrefix("prefix8888"))
            println("-------------")
            println(hasPrefix("p2refix8888"))
            println("-------------")
        }

        fun hasPrefix(x: Any) = when(x) {
            is String -> x.startsWith("prefix")
            else -> false
        }
    }
}