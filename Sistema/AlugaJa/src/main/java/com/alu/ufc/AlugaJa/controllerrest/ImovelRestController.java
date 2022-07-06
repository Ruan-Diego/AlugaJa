package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;
import com.alu.ufc.AlugaJa.service.ImovelServiceIMPL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;


@RestController
@RequestMapping(value = "/api/v1/")
@Api (value="API Rest de imóveis")
public class ImovelRestController {
    @Autowired
    private ImovelServiceIMPL imovelServiceIMPL;

    @GetMapping(value = "imoveis/")
    @ApiOperation(value = "Retorna todos os imóveis do sistema")
    public ResponseEntity<List<ImovelDTO>> getAll() throws Exception {
        return new ResponseEntity<List<ImovelDTO>>(imovelServiceIMPL.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value = "Retorna um imóvel específico")
    public ResponseEntity<ImovelDTO> find(@PathVariable String id_imovel) throws Exception {
        return imovelServiceIMPL.get(id_imovel);
    }


    @PutMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value = "Atualiza informação de um imóvel existente")
    public ResponseEntity<String> update(@RequestBody Imovel imovel, @PathVariable String id_imovel) throws Exception {
        return imovelServiceIMPL.update(imovel, id_imovel);
    }

    @PostMapping(value = "imoveis/")
    @ApiOperation(value = "Cria um imóvel no sistema")
    public ResponseEntity<String> save2(@RequestBody Imovel imovel) throws Exception {
        return imovelServiceIMPL.save(imovel);
    }

    @DeleteMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value = "Deleta um imóvel do sistema")
    public ResponseEntity<String> delete(@PathVariable String id_imovel, @RequestBody String id_locador) throws Exception {
        return imovelServiceIMPL.delete(id_imovel, id_locador);
    }
}