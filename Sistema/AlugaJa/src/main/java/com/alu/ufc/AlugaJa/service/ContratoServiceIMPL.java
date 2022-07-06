package com.alu.ufc.AlugaJa.service;

import com.alu.ufc.AlugaJa.commons.GenericServiceAPI;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.repositories.API.ContratoRepositoryAPI;
import com.alu.ufc.AlugaJa.repositories.ContratoRepository;
import com.alu.ufc.AlugaJa.repositories.ImovelRepository;
import com.alu.ufc.AlugaJa.repositories.UsuarioRepository;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alu.ufc.AlugaJa.commons.GenericServiceIMPL;
import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoServiceIMPL{

    @Autowired
    ContratoRepository contratoRep;
    @Autowired
    ImovelRepository imovelRep;

    @Autowired
    UsuarioRepository usuarioRep;


//    public ResponseEntity<String> update(Contrato contrato, String id) throws Exception {
//        String resultado = ("");
//        int alt = 0;
//        ContratoDTO dto = contratoRep.get(id);
//        if(id == null || dto == null){
//            return new ResponseEntity<String>("ID inválido tente novament", HttpStatus.BAD_REQUEST);
//        }
//
//        if (dto.getId_usuario().equals(contrato.getId_usuario())){} else {return new ResponseEntity<>("Você não pode alterar um Id locatario do contrato", HttpStatus.FORBIDDEN);}
//        if (dto.getValidade().equals(contrato.getValidade())){} else {alt++;}
//        if (dto.getValorImovel() == (contrato.getValorImovel())){} else {alt++;}
//        if(dto.getId_imovel() == (contrato.getId_imovel())){} else {return new ResponseEntity<>("Você não pode alterar um Id imovel do contrato", HttpStatus.FORBIDDEN);}
//
//
//        if(alt == 0){
//            resultado = "Não houveram alterações, mude algum campo e tente novamente";
//            return new ResponseEntity<String>(resultado, HttpStatus.NO_CONTENT);
//        }
//        else{
//            contratoRep.update(contrato, id);
//            resultado = "alterado com sucesso";
//            return new ResponseEntity<String>(resultado, HttpStatus.OK);
//        }
//    }

    public ResponseEntity<String> save(Contrato contrato) throws Exception {

        String resultado = new String("");

        if (contrato.getId_imovel() == null) {
            resultado = "Não foi possível registrar o imóvel. MOTIVO: Imóvel não encontrado no banco de dados" + "\n";
        }
        if (contrato.getValidade() == 0) {
            resultado = "Você precisa preencher todos os campos para criar o contrato, o campo 'Validade' está vazio" + "\n";
        }
        if (contrato.getValor_imovel() == 0) {
            resultado = "Você precisa preencher todos os campos para criar o contrato, o campo 'ValorImovel' está vazio" + "\n";
        }

        if(contrato.getId_usuario() == null){
            return new ResponseEntity<>("Locatario inexistente", HttpStatus.BAD_REQUEST);
        }

        if(usuarioRep.get(contrato.getId_usuario()) == null){
            resultado = "Seu locatario não existe no nosso sistema" + "\n";
        }

        if(imovelRep.get(contrato.getId_imovel())== null){
            resultado = "Seu imóvel ainda não foi adicionado no nosso sistema" + "\n";
        }

        if(contrato.getValidade() < 6){
            resultado = "A válidade de um contrato deve ser no mínimo 6 meses" + "\n";
        }

        if (resultado.equals("")) {
            ImovelDTO dto = imovelRep.get(contrato.getId_imovel());
            Imovel imovel = new Imovel(dto.getMensalidade(), dto.getUF(), true, dto.getBairro(), dto.getBanheiros(), dto.getQuartos(), dto.getLogradouro(), dto.getNumero(), dto.getCidade(), dto.getId_usuario());
            imovelRep.update(imovel, dto.getId());
            String id = contratoRep.save(contrato);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>(resultado, HttpStatus.BAD_REQUEST);
        }


    }

    public ResponseEntity<String> delete(String id_contrato, String id_imovel, String id_locador) throws Exception {
        ContratoDTO contrato = contratoRep.get(id_contrato);
        if(contrato == null || id_contrato==null){
            return new ResponseEntity<String>("ID do contrato inválido, tente novamente", HttpStatus.NOT_FOUND);
        }

        if(id_imovel == null || imovelRep.get(id_imovel) == null){
            return new ResponseEntity<>("ID do imóvel inválido", HttpStatus.BAD_REQUEST);
        }

        if(id_locador == null || usuarioRep.get(id_locador) == null){
            return new ResponseEntity<>("ID do locador inválido", HttpStatus.BAD_REQUEST);
        }

        List<ImovelDTO> imoveis = imovelRep.getAll();
        for(int i=0; imoveis.size()>i; i++){
            if(imoveis.get(i).getId_usuario().equals(id_locador)){
                if(id_imovel.equals(imoveis.get(i).getId())) {
                    if (contrato.getId_imovel().equals(imoveis.get(i).getId())) {
                        contratoRep.delete(id_contrato);
                        ImovelDTO dto = imovelRep.get(contrato.getId_imovel());
                        Imovel imovel = new Imovel(dto.getMensalidade(), dto.getUF(), false, dto.getBairro(), dto.getBanheiros(), dto.getQuartos(), dto.getLogradouro(), dto.getNumero(), dto.getCidade(), dto.getId_usuario());
                        imovelRep.update(imovel, dto.getId());
                        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
                    }
                }
            }
        }
        return new ResponseEntity<>("Você não pode excluir imóveis que não são seus", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<List<ContratoDTO>>allContratos(String id_locador) throws Exception{
        List<ContratoDTO> all = contratoRep.getAll();
        List<ImovelDTO> idImovel = imovelRep.getAll();
        List<String> imoveis = new ArrayList<>();
        List<ContratoDTO> saida = new ArrayList<>();

        if(id_locador == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for(int i=0; i<idImovel.size(); i++){
            if(idImovel.get(i).getId_usuario().equals(id_locador)){
                imoveis.add(idImovel.get(i).getId());
            }
        }
        for(int i=0; i<all.size(); i ++){
            for(int j=0; j<imoveis.size(); j++){
                if(all.get(i).getId_imovel().equals(imoveis.get(j))){
                    saida.add(all.get(i));
                }
            }
        }
        if(saida == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(saida, HttpStatus.OK);
    }

}