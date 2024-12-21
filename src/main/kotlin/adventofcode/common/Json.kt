package adventofcode.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

object Json {
    val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
}
