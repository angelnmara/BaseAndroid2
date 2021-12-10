package com.lamarrulla.mytiendita.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class DatosCliente(
    @PrimaryKey
    var idDatosCliente: Int,
    var nombreCliente: String,
    var nombreUsuario: String,
    var telefonoCliente: String
):Parcelable
