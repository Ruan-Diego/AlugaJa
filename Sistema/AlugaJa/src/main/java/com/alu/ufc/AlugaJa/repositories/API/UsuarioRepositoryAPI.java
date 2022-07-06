package com.alu.ufc.AlugaJa.repositories.API;

import com.alu.ufc.AlugaJa.commons.GenericServiceAPI;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioRepositoryAPI extends GenericServiceAPI<Usuario, UsuarioDTO> {
}

