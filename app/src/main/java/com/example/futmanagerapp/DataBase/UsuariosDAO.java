package com.example.futmanagerapp.DataBase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class UsuariosDAO {

    @Insert
    public abstract void insertarUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE usuario = :usuario AND contraseña = :contraseña")
    public abstract Usuario autentificar(String usuario, String contraseña);

    @Query("SELECT * FROM Usuario WHERE usuario= :usuario")
    public abstract Usuario comprobarUsuarioDisponible(String usuario);
}
