package com.alu.ufc.AlugaJa.service;

import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.repositories.API.UsuarioRepositoryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceIMPL {
    @Autowired
    UsuarioRepositoryAPI usuarioRep;
    public ResponseEntity<String> update(Usuario usuario, String id) throws Exception {
        String resultado = ("");
        int alt = 0;
        UsuarioDTO dto = usuarioRep.get(id);
        if(id == null || dto == null){
            return new ResponseEntity<String>("ID inválido tente novament", HttpStatus.BAD_REQUEST);
        }

        if(dto.getNome().equals(usuario.getNome())){} else { alt++;}
        if(dto.getCidade().equals(usuario.getCidade())){} else { alt++;}
        if(dto.getCpf() == usuario.getCpf()){} else { alt++;}
        if(dto.getSenha() == usuario.getSenha()){} else { alt++;}
        if(dto.getEmail() == usuario.getEmail()){} else { alt++;}
        if(dto.getContato() == usuario.getContato()){} else { alt++;}
        if(dto.getUF().equals(usuario.getUF())){} else { alt++;}

        if(alt == 0){
            resultado = "Não houveram alterações, mude algum campo e tente novamente";
            return new ResponseEntity<String>(resultado, HttpStatus.NO_CONTENT);
        }
        else{
            usuarioRep.update(usuario, id);
            resultado = "alterado com sucesso";
            return new ResponseEntity<String>(resultado, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> save(Usuario usuario) throws Exception {

        String resultado = new String("");

        if (usuario.getContato() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Bairro' está vazio" + "\n";
        }
        if (usuario.getCpf() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Banheiros' está vazio" + "\n" ;
        }
        if (usuario.getEmail() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, Campo 'Alugado' não informado" + "\n";
        }
        if (usuario.getCidade() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Cidade' está vazio" + "\n";
        }
        if(usuario.getCidade() != null) {
            if (usuario.getCidade().equals("Banabuiú") || usuario.getCidade().equals("Choró") || usuario.getCidade().equals("Deputado Irapuan Pinheiro") || usuario.getCidade().equals("Ibaretama") ||
                    usuario.getCidade().equals("Ibicuitinga") || usuario.getCidade().equals("Milhã") || usuario.getCidade().equals("Mombaça") || usuario.getCidade().equals("Pedra Branca") ||
                    usuario.getCidade().equals("Piquet Carneiro") || usuario.getCidade().equals("Quixadá") || usuario.getCidade().equals("Quixeramobim") || usuario.getCidade().equals("Senador Pompeu") ||
                    usuario.getCidade().equals("Solonópole")) {
            } else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, verifique se escreveu corretamente ou aguarde novas atualizações :)" + "\n";
            }
        }
        if (usuario.getSenha() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Logradouro' está vazio" + "\n";
        }
        if (usuario.getNome() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Mensalidade' está vazio" + "\n";
        }
        if (usuario.getUF() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'UF' está vazio" + "\n";
        }
        if(usuario.getUF() != null) {
            if (usuario.getUF().equals("CE")) {
            }
            else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, aguarde novas atualizações :)" + "\n";
            }
        }
        if (resultado.equals("")) {
            String id = usuarioRep.save(usuario);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>(resultado, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> delete(String id) throws Exception {
        UsuarioDTO usuario = usuarioRep.get(id);
        if(usuario == null || id==null){
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        }
        else{
            usuarioRep.delete(id);
            return new ResponseEntity<String>("Deletado com sucesso",HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<UsuarioDTO> get(String id) throws Exception {
        UsuarioDTO dto = usuarioRep.get(id);
        if(dto == null || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    public List<UsuarioDTO> getAll() throws Exception {
        return usuarioRep.getAll();
    }
}
