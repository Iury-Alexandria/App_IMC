package com.app.iury.appimc

import java.io.Serializable

data class NavegationData(
    var imcResult: String,
    var mensageImc: String,
    var name: String? = ""
): Serializable
