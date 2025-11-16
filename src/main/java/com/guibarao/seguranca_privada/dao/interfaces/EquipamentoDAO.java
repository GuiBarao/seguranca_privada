package com.guibarao.seguranca_privada.dao.interfaces;

import com.guibarao.seguranca_privada.models.ItemEstoque;

import java.util.List;

public interface EquipamentoDAO {


    public Long cadastrarEquipamento(ItemEstoque dados);

    public void alterarQuantidade(int id_item, int alteracao);

    public List<ItemEstoque> listarEquipamentosEmFalta();

}