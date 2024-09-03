package com.example.recycler.model

import java.util.UUID

data class PirateFlag (
    val id: UUID = UUID.randomUUID()
) : BaseItem()