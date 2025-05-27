package com.example.l2teste.controllers;

import com.example.l2teste.model.dto.Box;
import com.example.l2teste.model.dto.Dimensoes;
import com.example.l2teste.model.dto.Produto;
import com.example.l2teste.model.dto.request.PedidoRequest;
import com.example.l2teste.model.dto.response.BoxResponse;
import com.example.l2teste.model.dto.response.PedidoResponse;
import com.example.l2teste.model.dto.response.PedidosWrapper;
import com.example.l2teste.service.PackingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackingControllerTest {

    @Mock
    private PackingService packingService;

    @InjectMocks
    private PackingController packingController;

    private PedidoRequest validRequest;
    private PedidoRequest invalidRequest;
    private PedidosWrapper expectedResponse;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        // Configuração de objetos de teste
        validRequest = new PedidoRequest();
        // Configure validRequest com dados válidos conforme sua implementação

        invalidRequest = null; // Ou um request com dados inválidos

        String jsonStringRequest = "{\n" +
                "  \"pedidos\": [\n" +
                "    {\n" +
                "      \"pedido_id\": 1,\n" +
                "      \"produtos\": [\n" +
                "        {\"produto_id\": \"PS5\", \"dimensoes\": {\"altura\": 40, \"largura\": 10, \"comprimento\": 25}}\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";



        List<PedidoResponse> pedidoResponses = new ArrayList<>();
        Box box =  new Box(30,40,80,"Caixa 1");
        Produto  produto= new Produto();
        produto.setProduto_id("PS5");
        Dimensoes dimensoes = new Dimensoes();
        dimensoes.setAltura(40);
        dimensoes.setLargura(80);
        dimensoes.setComprimento(25);
        produto.setDimensoes(dimensoes);
        box.addProduto(produto);
        BoxResponse[] boxes = new BoxResponse[1];
        boxes[0] = new BoxResponse(box);
        pedidoResponses.add(new PedidoResponse("pedido_id 1",boxes));
        PedidosWrapper wrapper = new PedidosWrapper();
        wrapper.setPedidos(pedidoResponses);
        expectedResponse = wrapper;
        ObjectMapper mapper = new ObjectMapper();
        validRequest = mapper.readValue(jsonStringRequest,PedidoRequest.class);

    }

    @Test
    void packBoxTest_OKResponse() throws JsonProcessingException {
        // Arrange
        when(packingService.packing(validRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Object> response = packingController.packBox(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(packingService, times(1)).packing(validRequest);
    }


}