package com.example.l2teste.service;

import com.example.l2teste.model.dto.Box;
import com.example.l2teste.model.dto.Pedido;
import com.example.l2teste.model.dto.Produto;
import com.example.l2teste.model.dto.request.PedidoRequest;
import com.example.l2teste.model.dto.response.BoxResponse;
import com.example.l2teste.model.dto.response.PedidoResponse;
import com.example.l2teste.model.dto.response.PedidosWrapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PackingService {


    public PedidosWrapper packing(PedidoRequest pedidosRequest) {
      //HashMap<String ,List<BoxResponse>> mapBoxes = new HashMap<>();
      List<PedidoResponse> pedidoResponses = new ArrayList<>();
        for(Pedido pedido : pedidosRequest.getPedidos()){
                List<Box> boxes =  packingProdutos(pedido);
                List<BoxResponse> boxResponses = new ArrayList<>();
                for(Box box : boxes){
                    BoxResponse boxResponse = new BoxResponse(box);
                    boxResponses.add(boxResponse);
                }
                pedidoResponses.add(new PedidoResponse("pedido_id " + Integer.toString(pedido.getPedido_id()),boxResponses.toArray(new BoxResponse[boxResponses.size()])));
                //mapBoxes.put("pedido_id " + Integer.toString(pedido.getPedido_id()),boxResponses);
      }
        PedidosWrapper pedidosWrapper = new PedidosWrapper();
        pedidosWrapper.setPedidos(pedidoResponses);
      return pedidosWrapper;

    }

    public List<Box> packingProdutos (Pedido pedido) {
        Box []box = new Box[3];
        box[0] = new Box(30,40,80,"Caixa 1");
        box[1] = new Box(80,50,40,"Caixa 2");
        box[2] = new Box(50,80,60,"Caixa 3");
        Box boxNotFind = new Box(0,0,0,"null");
        boxNotFind.setObservacao("Produto não cabe em nenhuma caixa disponível");
        LinkedList<Box> boxes = new LinkedList<>();
        for(Produto produto : pedido.getProdutos()){
           int pos =   findBox(box,produto);
           if(pos != -1) {
               box[pos].addProduto(produto);
           }
           else if(pos==-1) {
               boxNotFind.addProduto(produto);
           }
        }
        for(Box b : box)
        {
            if(b.getProdutoIds().size()>0) {
                boxes.add(b);
            }
        }
        if(boxNotFind.getProdutoIds().size()>0) {
            boxes.add(boxNotFind);
        }
      return boxes;
    }
    public int findBox(Box[] boxes, Produto produto) {
        for (int i = 0; i < boxes.length; i++) {
            if(boxes[i].canAdd(produto)) {
                return i;
            }
        }
        return -1;
    }

}