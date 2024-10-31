package com.ag.projects.domain.model.address

import java.io.Serializable

data class AddressesResponse(
    val data: List<DefaultAddress>,
    val status: String,
    val message: String
) : Serializable

data class AddAddressResponse(
    val data: AddAddressModel,
    val status: String,
    val message: String
)

data class AddAddressModel(
    val address: DefaultAddress
)
