package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;

import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.alu.ufc.AlugaJa.service.ContratoServiceIMPL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
@Api(value="API Rest de contratos")
public class ContratoRestController {
    @Autowired
    private ContratoServiceIMPL contratoServiceIMPL;

    @GetMapping(value = "contratos/")
    @ApiOperation(value = "Retorna todos os contratos do sistema")
    public ResponseEntity<List<ContratoDTO>> getAllContratos(@RequestBody String id_locador) throws Exception {
        return contratoServiceIMPL.allContratos(id_locador);
    }

    @PostMapping(value="contratos/")
    @ApiOperation(value = "Aluga um imóvel criando um contrato")
    public ResponseEntity<String> aluga(@RequestBody Contrato contrato) throws Exception{
        return contratoServiceIMPL.save(contrato);
    }

    @DeleteMapping(value="contratos/{id_contrato}")
    @ApiOperation(value = "Deleta um contrato de imóvel")
    public ResponseEntity<String> deleteContrato(@PathVariable String id_contrato, @RequestParam String id_imovel, @RequestParam String id_locador) throws Exception{
        return contratoServiceIMPL.delete(id_contrato, id_imovel, id_locador);
    }
}