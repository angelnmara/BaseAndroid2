package com.lamarrulla.mytiendita.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lamarrulla.mytiendita.data.model.DatosCliente

@Dao
interface DatosClienteDao {
    @Query("SELECT * FROM DatosCliente")
    fun getAll(): List<DatosCliente>
    @Query("SELECT * FROM DatosCliente WHERE idDatosCliente = :idDatosCliente")
    fun findById(idDatosCliente:Int): DatosCliente
    @Insert
    fun insert(vararg datosCliente: DatosCliente)
    @Insert
    fun insertList(datosClienteList: List<DatosCliente>): List<Long>
    @Delete
    fun delete(datosCliente: DatosCliente)
}