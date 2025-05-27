package com.example.l2teste.service;

import com.example.l2teste.model.dto.Box;
import com.example.l2teste.model.dto.Dimensoes;
import com.example.l2teste.model.dto.Pedido;
import com.example.l2teste.model.dto.Produto;
import com.example.l2teste.model.dto.request.PedidoRequest;
import com.example.l2teste.model.dto.response.BoxResponse;
import com.example.l2teste.model.dto.response.PedidoResponse;
import com.example.l2teste.model.dto.response.PedidosWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PackingServiceTest {

    @InjectMocks
    private PackingService packingService;

    private PedidoRequest pedidoRequest;
    private Pedido pedido;
    private Produto produtoQueCabe;
    private Produto produtoQueNaoCabe;

    @BeforeEach
    void setUp() {
        // Configuração de objetos de teste
        produtoQueCabe = new Produto();
        Dimensoes dimensoes = new Dimensoes(new int[]{20, 30, 40});
        produtoQueCabe.setDimensoes(dimensoes); // Cabe na Caixa 1
        dimensoes = new Dimensoes(new int[]{100, 100, 100});
        produtoQueNaoCabe = new Produto();
        produtoQueNaoCabe.setDimensoes(dimensoes); // Não cabe em nenhuma caixa

        pedido = new Pedido();
        pedido.setPedido_id(1);
        pedido.setProdutos(new ArrayList<>(List.of(produtoQueCabe, produtoQueNaoCabe)));

        pedidoRequest = new PedidoRequest();
        pedidoRequest.setPedidos(new ArrayList<>(List.of(pedido)));
    }

    @Test
    void packing_ShouldReturnCorrectWrapperStructure() {
        // Act
        PedidosWrapper result = packingService.packing(pedidoRequest);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPedidos());
        assertEquals(1, result.getPedidos().size());

        PedidoResponse pedidoResponse = result.getPedidos().get(0);
        assertEquals("pedido_id 1", pedidoResponse.getId());
    }

    @Test
    void packing_ShouldCreateBoxesForProducts() {
        // Act
        PedidosWrapper result = packingService.packing(pedidoRequest);
        BoxResponse[] caixas = result.getPedidos().get(0).getBoxResponse();

        // Assert
        assertEquals(2, caixas.length); // 1 caixa normal + 1 caixa para produtos que não cabem

        boolean foundNormalBox = false;
        boolean foundSpecialBox = false;

        for (BoxResponse box : caixas) {
            if (box.getId().equals("null")) {
                assertEquals("Produto não cabe em nenhuma caixa disponível", box.getObservacao());
                foundSpecialBox = true;
            } else {
                assertTrue(box.getId().startsWith("Caixa "));
                foundNormalBox = true;
            }
        }

        assertTrue(foundNormalBox);
        assertTrue(foundSpecialBox);
    }

    @Test
    void packingProdutos_ShouldReturnCorrectNumberOfBoxes() {
        // Act
        List<Box> boxes = packingService.packingProdutos(pedido);

        // Assert
        assertEquals(2, boxes.size()); // 1 caixa normal + 1 caixa especial
    }

    @Test
    void packingProdutos_ShouldAddProductsToCorrectBoxes() {
        // Act
        List<Box> boxes = packingService.packingProdutos(pedido);

        // Assert
        Box normalBox = boxes.stream().filter(b -> !b.getId().equals("null")).findFirst().orElse(null);
        Box specialBox = boxes.stream().filter(b -> b.getId().equals("null")).findFirst().orElse(null);

        assertNotNull(normalBox);
        assertNotNull(specialBox);

        assertEquals(1, normalBox.getProdutoIds().size());
        assertEquals(1, specialBox.getProdutoIds().size());
    }

    @Test
    void findBox_ShouldReturnCorrectBoxIndex() {
        // Arrange
        Box[] boxes = new Box[3];
        boxes[0] = new Box(30, 40, 80, "Caixa 1");
        boxes[1] = new Box(80, 50, 40, "Caixa 2");
        boxes[2] = new Box(50, 80, 60, "Caixa 3");

        Produto produtoPequeno = new Produto();
        Dimensoes dimensoes = new Dimensoes(new int[]{20, 30, 40});
        produtoPequeno.setDimensoes(dimensoes); // Cabe na Caixa 1
        dimensoes = new Dimensoes(new int[]{100, 100, 100});
        Produto produtoGrande = new Produto();
        produtoGrande.setDimensoes(dimensoes); // Não cabe

        // Act & Assert
        assertEquals(0, packingService.findBox(boxes, produtoPequeno));
        assertEquals(-1, packingService.findBox(boxes, produtoGrande));
    }

    @Test
    void packing_WithEmptyRequest_ShouldReturnEmptyWrapper() {
        // Arrange
        PedidoRequest emptyRequest = new PedidoRequest();
        emptyRequest.setPedidos(new ArrayList<>());

        // Act
        PedidosWrapper result = packingService.packing(emptyRequest);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPedidos());
        assertTrue(result.getPedidos().isEmpty());
    }

    @Test
    void packing_WithMultiplePedidos_ShouldProcessAll() {
        // Arrange
        Pedido pedido2 = new Pedido();
        pedido2.setPedido_id(2);
        pedido2.setProdutos(new ArrayList<>(List.of(produtoQueCabe)));

        pedidoRequest.getPedidos().add(pedido2);

        // Act
        PedidosWrapper result = packingService.packing(pedidoRequest);

        // Assert
        assertEquals(2, result.getPedidos().size());
        assertEquals("pedido_id 1", result.getPedidos().get(0).getId());
        assertEquals("pedido_id 2", result.getPedidos().get(1).getId());
    }
}