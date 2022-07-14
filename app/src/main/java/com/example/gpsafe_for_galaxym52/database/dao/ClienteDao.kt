package com.example.gpsafe_for_galaxym52.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gpsafe_for_galaxym52.model.clientes

@Dao
interface ClienteDao {


    @Query("SELECT * FROM clientes")
    fun buscaTodos() :List<clientes>

    @Query("SELECT * FROM clientes WHERE cpf = :cpf")
    fun findClientePorCPF(cpf: String?): List<clientes>

    @Query("Select * from clientes where cpf like :cpf")
    fun getSearchResults(cpf : String) : LiveData<List<clientes>>

    @Query("SELECT * FROM clientes")
    fun buscaUm() : List<clientes>

    @Insert
    fun salva(vararg Clientes: clientes)

    @Delete
    fun remove(vararg Clientes: clientes)

    @Update
    fun atualiza(vararg Clientes: clientes)

}