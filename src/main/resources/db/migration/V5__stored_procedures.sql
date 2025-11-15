DROP PROCEDURE IF EXISTS registra_equipamento_estoque;
DROP PROCEDURE IF EXISTS gerar_parcela;
DROP PROCEDURE IF EXISTS itens_em_falta;
DROP PROCEDURE IF EXISTS altera_quantidade_item;
DROP PROCEDURE IF EXISTS segurancas_disponiveis;

DELIMITER $$

CREATE procedure registra_equipamento_estoque(t VARCHAR(50), n VARCHAR(100), q int)
begin
    INSERT INTO item_estoque (telefone_fornecedor, nome, quantidade) VALUES (t, n, q);

    SELECT 'Equipamento registrado no estoque!';
end $$

CREATE procedure gerar_parcela(cliente INT, v DECIMAL, d DATE)
   begin

        INSERT INTO cobranca (id_cliente, valor, data_vencimento) VALUES
        (cliente, v, d);

        SELECT 'Cobrança registrada com sucesso!';

   end $$

CREATE procedure itens_em_falta()

    begin

        SELECT * FROM item_estoque WHERE quantidade < 5;

    end $$

 CREATE PROCEDURE altera_quantidade_item(id_item int, alteracao int)

    begin
        DECLARE quantidade_atual int;
        DECLARE nova_quantidade int;

        SELECT quantidade INTO quantidade_atual FROM item_estoque WHERE id = id_item;

        SET nova_quantidade = quantidade_atual + alteracao;

        UPDATE item_estoque SET quantidade = nova_quantidade WHERE id = id_item;

        SELECT CONCAT('Alteração bem sucedida. Quantidade atual: ', nova_quantidade) AS mensagem;
    end $$


CREATE PROCEDURE segurancas_disponiveis(data_busca DATE)
begin

    SELECT nome_completo FROM usuario WHERE id NOT IN (
        SELECT id_seguranca FROM sem_disponibilidade
        WHERE data_busca BETWEEN data_inicial AND data_final
    );

end $$

DELIMITER ;