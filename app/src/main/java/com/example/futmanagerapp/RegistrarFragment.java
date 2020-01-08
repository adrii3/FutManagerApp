package com.example.futmanagerapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarFragment extends Fragment {

    private AutenticationViewModel autenticacionViewModel;

    private EditText usuarioEditText, contrasenyaEditText;
    private Button registrarButton;


    public RegistrarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticacionViewModel= ViewModelProviders.of(requireActivity()).get(AutenticationViewModel.class);
        usuarioEditText = view.findViewById(R.id.usuario_edit_text_registro);
        contrasenyaEditText = view.findViewById(R.id.contraseña_edit_text_registro);
        registrarButton = view.findViewById(R.id.button_registro_registrar);


        autenticacionViewModel.inicarRegistro();


        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticacionViewModel.crearCuentaEIniciarSesion(usuarioEditText.getText().toString(), contrasenyaEditText.getText().toString());
            }
        });

        autenticacionViewModel.estadoRegistroMLD.observe(getViewLifecycleOwner(), new Observer<AutenticationViewModel.EstadoRegistro>() {
            @Override
            public void onChanged(AutenticationViewModel.EstadoRegistro estadoRegistro) {
                switch (estadoRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                        Toast.makeText(getContext(), "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        autenticacionViewModel.estadoAutenticacionMLD.observe(getViewLifecycleOwner(), new Observer<AutenticationViewModel.EstadoAutenticacion>() {
            @Override
            public void onChanged(AutenticationViewModel.EstadoAutenticacion estadoAutenticacion) {
                switch (estadoAutenticacion){
                    case AUTENTICADO:
                        Navigation.findNavController(view).navigate(R.id.inicioFragment);
                        break;
                }
            }
        });



    }
}
