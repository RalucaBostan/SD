package com.sd.laborator.interfaces

import com.sd.laborator.pojo.Person

interface AgendaCachingService {
    fun exists(query:String): Person?
    fun addPerson(query: String,data:Person)
}