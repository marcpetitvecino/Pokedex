package com.marcpetit.pokedex.common

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = VISIBLE
    } else {
        this.visibility = GONE
    }
}
