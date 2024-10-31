package com.ag.projects.domain.model.address

import java.io.Serializable

data class DefaultAddress(
    val country: String?,
    val id: Int?,
    val is_completed: Boolean?,
    var is_default: Boolean?,
    val is_have_available_service: Boolean?,
    val is_notify: Boolean?,
    val lat: Double?,
    val lng: Double?,
    val location_description: String?,
    val phone: String?,
    val phone_code: String?,
    val place_description: String?,
    val type: String?,
    val building: String?,
    val floor: String?,
    val apartment: String?,
    val is_verify: Boolean?,
    val step: String?,
    val is_active:String
) : Serializable