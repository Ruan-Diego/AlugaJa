package com.alu.ufc.AlugaJa.repositories;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.repositories.API.UsuarioRepositoryAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRepository extends GenericServiceIMPL<Usuario, UsuarioDTO> implements UsuarioRepositoryAPI {
    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("usuarios");
    }
}

