package com.example.l2teste.controllers;
import com.example.l2teste.service.PackingService;
import com.example.l2teste.model.dto.request.PedidoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Packing Controller", description = "Endpoints para operações de empacotamento")
public class PackingController {


@Autowired
private PackingService packingService;

    @Operation(summary = "Empacota itens conforme solicitação", description = "Recebe um pedido e retorna a melhor forma de empacotamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empacotamento realizado com sucesso",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content)
    })
    @PostMapping("/pack")
    public ResponseEntity<Object> packBox(@RequestBody PedidoRequest request) {

        return ResponseEntity.ok().body(packingService.packing(request));
    }

}
