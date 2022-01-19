package com.example.classwork17

import java.io.Serializable

class RvModel:Serializable {

    var name:String?=null
    var number:String?=null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }
}