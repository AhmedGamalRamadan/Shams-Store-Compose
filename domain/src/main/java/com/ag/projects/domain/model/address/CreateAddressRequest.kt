package com.ag.projects.domain.model.address

data class CreateAddressRequest(
    var lat: Double? = null,
    var lng: Double? = null,
    var location_description: String? = null,
    var place_description: String? = null,
    var type: String? = null,
    var is_default: Int? = null,
    var building: String? = null,
    var floor: String? = null,
    var apartment: String? = null,
    var _method: String? = null
)