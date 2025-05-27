package com.example.l2teste.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class PedidoResponse {
    public PedidoResponse(String id, BoxResponse[] boxes  ) {
        this.id = id;
        this.setBoxResponse(boxes);
    }
    @JsonProperty("pedido_id")
    String id;
    @JsonProperty("Caixas")
    BoxResponse [] boxResponse;

    public void setId(int id)
    {
       this.id = "Pedido " + Integer.toString( id);
    }
}
