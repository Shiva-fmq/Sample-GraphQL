package com.fmq.mysamplekotlingraphql.data

import GetContinentsQuery
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails
import com.fmq.mysamplekotlingraphql.domain.DetailedCountry
import com.fmq.mysamplekotlingraphql.domain.SimpleCountry

fun GetContinentsQuery.Continent.toContinent(): ContinentDetails {
    return ContinentDetails(
        code = code,
        name = name,
    )
}

/*
fun FindCountriesOfAContinentQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = name,
        emoji = emoji,
        phone = phone,
        native = native,
        currency = currency?:"No Currency"
    )
}*/
