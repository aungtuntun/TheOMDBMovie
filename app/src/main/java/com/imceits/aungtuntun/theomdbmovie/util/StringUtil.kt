package com.imceits.aungtuntun.theomdbmovie.util

import com.imceits.aungtuntun.theomdbmovie.data.model.Genre
import java.lang.StringBuilder

class StringUtil {

fun  runtime(runtime: Int): String? {
   // return runtime.div(60).toString() + "hr(s) " + runtime.rem(60).toString() +"min(s)"
    var playtime: String? = ""
    if (runtime > 0) {
        val hr = runtime.div(60)
        val min = runtime.rem(60)
        playtime = "" + hr + "hr(s) " + min + "min(s)"
    }
    return playtime
}

    fun genres(list: List<Genre>) : String {
        val genres = StringBuilder()
       for (item in list) {
           if (genres.isNotEmpty()) {
               genres.append(",")
           }
           genres.append(item.name)
       }
        return genres.toString()
    }
}