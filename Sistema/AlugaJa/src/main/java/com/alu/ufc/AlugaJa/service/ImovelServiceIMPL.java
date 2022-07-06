package com.alu.ufc.AlugaJa.service;

import com.alu.ufc.AlugaJa.repositories.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;


import java.util.ArrayList;
import java.util.List;

@Service
public class ImovelServiceIMPL{

    @Autowired
    ImovelRepository imovelRep;

    public ResponseEntity<String> update(Imovel imovel, String id_imovel) throws Exception {
        String resultado = ("");
        int alt = 0;
        ImovelDTO dto = imovelRep.get(id_imovel);
        if(id_imovel == null || dto == null){
            return new ResponseEntity<String>("ID inválido tente novament", HttpStatus.BAD_REQUEST);
        }

        if(!dto.getBairro().equals(imovel.getBairro())){ alt++;}
        if(!dto.getCidade().equals(imovel.getCidade())){ alt++;}
        if(!dto.getLogradouro().equals(imovel.getLogradouro())){ alt++;}
        if(dto.getMensalidade() != imovel.getMensalidade()){ alt++;}
        if(dto.getBanheiros() != imovel.getBanheiros()){ alt++;}
        if(dto.getQuartos() != imovel.getQuartos()){ alt++;}
        if(dto.getNumero() != imovel.getNumero()){ alt++;}
        if(!dto.getUF().equals(imovel.getUF())){ alt++;}
        if(!dto.getAlugado().equals(imovel.getAlugado())){ alt++;}

        if(alt == 0){
            resultado = "Não houveram alterações, mude algum campo e tente novamente";
            return new ResponseEntity<String>(resultado, HttpStatus.NO_CONTENT);
        }
        else{
            imovelRep.update(imovel, id_imovel);
            resultado = "alterado com sucesso";
            return new ResponseEntity<String>(resultado, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> save(Imovel imovel) throws Exception {

        String resultado = new String("");

        if (imovel.getBairro() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Bairro' está vazio" + "\n";
        }
        if (imovel.getBanheiros() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Banheiros' está vazio" + "\n" ;
        }
        if (imovel.getAlugado() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, Campo 'Alugado' não informado" + "\n";
        }
        if (imovel.getId_usuario() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, Campo 'Id_usuario' não informado" + "\n";
        }
        if (imovel.getCidade() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Cidade' está vazio" + "\n";
        }
        if(imovel.getCidade() != null) {
            if (imovel.getCidade().equals("Banabuiú") || imovel.getCidade().equals("Choró") || imovel.getCidade().equals("Deputado Irapuan Pinheiro") || imovel.getCidade().equals("Ibaretama") ||
                    imovel.getCidade().equals("Ibicuitinga") || imovel.getCidade().equals("Milhã") || imovel.getCidade().equals("Mombaça") || imovel.getCidade().equals("Pedra Branca") ||
                    imovel.getCidade().equals("Piquet Carneiro") || imovel.getCidade().equals("Quixadá") || imovel.getCidade().equals("Quixeramobim") || imovel.getCidade().equals("Senador Pompeu") ||
                    imovel.getCidade().equals("Solonópole")) {
            } else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, verifique se escreveu corretamente ou aguarde novas atualizações :)" + "\n";
            }
        }
        if (imovel.getLogradouro() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Logradouro' está vazio" + "\n";
        }
        if (imovel.getMensalidade() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Mensalidade' está vazio" + "\n";
        }
        if (imovel.getUF() == null) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'UF' está vazio" + "\n";
        }
        if(imovel.getUF() != null) {
            if (imovel.getUF().equals("CE")) {
            }
            else {
                resultado = "Esse aplicativo por enquanto trabalha apenas com a região do sertão central do Ceará, aguarde novas atualizações :)" + "\n";
            }
        }

        if (imovel.getQuartos() == 0) {
            resultado = "Você precisa preencher todos os campos para adicionar um imóvel, campo 'Quartos' está vazio" + "\n";
        }

        if (resultado.equals("")) {
            String id = imovelRep.save(imovel);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>(resultado, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> delete(String id_imovel, String id_locador) throws Exception {
        ImovelDTO imovel = imovelRep.get(id_imovel);
        if(imovel == null || id_imovel==null){
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        }
        if(imovel.getId_usuario().equals(id_locador)){
            imovelRep.delete(id_imovel);
            //contratoRep.delete(id_contrato);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Você não pode excluir imóveis que não são seus", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<ImovelDTO> get(String id) throws Exception {
        ImovelDTO dto = imovelRep.get(id);
        if(dto == null || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public List<ImovelDTO> getAll() throws Exception {
        return imovelRep.getAll();
    }

    public ResponseEntity<List<ImovelDTO>> imoveisDoUsuario(String id) throws Exception{
        List<ImovelDTO> dto = getAll();
        List<ImovelDTO> saida = new ArrayList<>();
        for(int i = 0; i<dto.size(); i++){
            if(dto.get(i).getId_usuario().equals(id)){
                saida.add(dto.get(i));
            }
        }
        return new ResponseEntity<List<ImovelDTO>>(saida, HttpStatus.OK);
    }
}