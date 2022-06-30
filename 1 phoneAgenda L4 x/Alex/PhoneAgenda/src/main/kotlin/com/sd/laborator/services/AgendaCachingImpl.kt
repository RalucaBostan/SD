package com.sd.laborator.services

import com.sd.laborator.interfaces.AgendaCachingService
import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class AgendaCachingImpl:AgendaCachingService {
    companion object{
        val agenda : ConcurrentHashMap<String, Pair<Person, Date>> = ConcurrentHashMap()
        val lock = Any()
        }

    override fun exists(query: String): Person? {
        synchronized(lock){
            var data:Pair<Person,Date>? = agenda.get(query)
            if(data != null && Date().time-data.second.time<1000*60*30){
                return data.first
            }else{
                return null
            }
        }
    }

    override fun addPerson(query: String, data: Person) {
        synchronized(lock){
            agenda.put(query, Pair(data,Date()))
        }
    }
}