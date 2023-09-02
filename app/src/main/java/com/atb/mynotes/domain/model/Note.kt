package com.atb.mynotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    val color: Int,
    val time: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
