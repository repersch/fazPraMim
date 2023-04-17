package br.edu.ifsp.scl.fazpramim.controller.response

import br.edu.ifsp.scl.fazpramim.enums.ServiceStatus
import java.time.LocalDate

data class ServiceResponse (
    var id: Long,
    var date: LocalDate,
    var hour: String,
    var requestDetails: String,
    var status: ServiceStatus,
    var rating: Double,
    var client: UserResponse,
    var provider: ProfileResponse
)
