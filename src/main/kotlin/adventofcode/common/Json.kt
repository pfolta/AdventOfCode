package adventofcode.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

val json: ObjectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
