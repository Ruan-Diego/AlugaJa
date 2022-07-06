package com.alu.ufc.AlugaJa.repositories.API;

import com.alu.ufc.AlugaJa.commons.GenericServiceAPI;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import org.springframework.stereotype.Service;

@Service
public interface ImovelRepositoryAPI extends GenericServiceAPI<Imovel, ImovelDTO> {
}

