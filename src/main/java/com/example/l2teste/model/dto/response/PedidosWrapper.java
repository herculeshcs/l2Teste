package com.example.l2teste.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PedidosWrapper {

    List<PedidoResponse> pedidos;

}
