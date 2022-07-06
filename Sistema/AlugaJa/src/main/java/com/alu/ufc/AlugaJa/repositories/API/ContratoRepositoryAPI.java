package com.alu.ufc.AlugaJa.repositories.API;

import com.alu.ufc.AlugaJa.commons.GenericServiceAPI;
import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContratoRepositoryAPI extends GenericServiceAPI<Contrato, ContratoDTO> {
}

