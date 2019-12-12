package com.example.futmanagerapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IniciarSesionFragment extends Fragment {


    private AutenticationViewModel autenticationViewModel;
    private Button iniciarSesionButton;
    private EditText usuarioEditText , contrasenyaEditText;


    public IniciarSesionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticationViewModel= ViewModelProviders.of(requireActivity()).get(AutenticationViewModel.class);

        usuarioEditText= view.findViewById(R.id.usuario_edit_text);
        contrasenyaEditText = view.findViewById(R.id.contraseña_edit_text);

        Button botonRegistro = view.findViewById(R.id.boton_registro);

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.registrarFragment);
            }
        });


        Button botonIniciarSesion = view.findViewById(R.id.button_inicar_Sesion);

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verificar el inicio sesion en la bbdd i dar acceso a la aplicacion
                autenticationViewModel.iniciarSesion(usuarioEditText.getText().toString(),contrasenyaEditText.getText().toString());


            }
        });

    }
}
