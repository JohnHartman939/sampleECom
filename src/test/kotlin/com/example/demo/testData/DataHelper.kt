package com.example.demo.testData

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter

class DataHelper {

    companion object{

        private val objectWriter: ObjectWriter = ObjectMapper().writer()

        fun dtoToJsonString(value: Any):String {
            return objectWriter.writeValueAsString(value)
        }
    }
}