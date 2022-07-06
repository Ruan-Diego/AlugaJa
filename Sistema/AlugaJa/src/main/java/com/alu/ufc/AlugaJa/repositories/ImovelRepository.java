package com.alu.ufc.AlugaJa.repositories;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.repositories.API.ImovelRepositoryAPI;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImovelRepository extends GenericServiceIMPL<Imovel, ImovelDTO> implements ImovelRepositoryAPI {
    @Autowired
    private Firestore firestore;

    @Override
    public CollectionReference getCollection() {
        return firestore.collection("imoveis");
    }
}

