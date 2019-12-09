package com.example.futmanagerapp.DataBase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey
    public String usuario;

    public String contraseña;

    public Usuario(String usuario, String contraseña){
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

}

