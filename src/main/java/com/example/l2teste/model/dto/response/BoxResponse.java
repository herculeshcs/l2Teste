package com.example.l2teste.model.dto.response;

import com.example.l2teste.model.dto.Box;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoxResponse {
    BoxResponse(){}
    public BoxResponse(Box box) {
        id = box.getId();
        produtos = box.getProdutoIds().toArray(new String[0]);
        observacao = box.getObservacao();
    }
    @JsonProperty("caixa_id")
    String id;
    String [] produtos;
    String observacao;
}
