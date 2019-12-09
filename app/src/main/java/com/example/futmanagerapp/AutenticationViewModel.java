package com.example.futmanagerapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.futmanagerapp.DataBase.Usuario;
import com.example.futmanagerapp.DataBase.UsuariosDAO;
import com.example.futmanagerapp.DataBase.UsuariosDataBase;


public class AutenticationViewModel extends AndroidViewModel {


    public enum EstadoAutenticacion {
        AUTENTICADO,
        NO_AUTENTICADO,
        AUTENTICACION_INVALIDA
    }
    public enum EstadoRegistro {
        REGISTRO_COMPLETO,
        NOMBRE_NO_DISPONIBLE,
        INICIO_REGISTRO
    }

    private UsuariosDAO usuariosDAO;
    public Usuario usuarioLogeado;


    public MutableLiveData<EstadoAutenticacion> estadoAutenticacionMLD = new MutableLiveData<>();
    public MutableLiveData<EstadoRegistro> estadoRegistroMLD = new MutableLiveData<>();



    public AutenticationViewModel(@NonNull Application application) {
        super(application);
        usuariosDAO = UsuariosDataBase.getInstance(application).dao();

        estadoAutenticacionMLD.setValue(EstadoAutenticacion.NO_AUTENTICADO);
        estadoRegistroMLD.setValue(EstadoRegistro.INICIO_REGISTRO);
    }

    public void inicarRegistro(){
        estadoRegistroMLD.postValue(EstadoRegistro.INICIO_REGISTRO);
    }

    public void CrearCuentaEIniciarSesion(final String usuario, final String contraseña){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario1 = usuariosDAO.comprobarUsuarioDisponible(usuario);
                if(usuario1==null){
                    usuariosDAO.insertarUsuario(new Usuario(usuario, contraseña));
                    estadoRegistroMLD.postValue(EstadoRegistro.REGISTRO_COMPLETO);
                    iniciarSesion(usuario, contraseña);
                }else{
                    estadoRegistroMLD.postValue(EstadoRegistro.NOMBRE_NO_DISPONIBLE);
                }
            }
        });
    }

    public void iniciarSesion(final String usuario, final String contraseña){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario1 = usuariosDAO.comprobarUsuarioDisponible(usuario);
                if(usuario1!=null){
                    usuarioLogeado = usuario1;
                    estadoAutenticacionMLD.postValue(EstadoAutenticacion.AUTENTICADO);
                }else{
                    estadoAutenticacionMLD.postValue(EstadoAutenticacion.AUTENTICACION_INVALIDA);
                }
            }
        });
    }

    public void cerrarSesion(){
        usuarioLogeado= null;
        estadoAutenticacionMLD.setValue(EstadoAutenticacion.NO_AUTENTICADO);
    }
}
