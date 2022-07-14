package com.example.gpsafe_for_galaxym52.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
class clientes(
    @PrimaryKey val cpf: String,
    val id: Long = 0L,
    val nome: String,
    val data: String,
    val valor: String,
    val contrato: String,
    val telefone: String,
)


