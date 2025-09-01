package com.guibarao.seguranca_privada.dtos.solicitacao;
import jakarta.validation.constraints.*;

public record SolicitacaoCadastroDTO(

   @NotBlank(message="A descrição da solicitação precisa ser informada.")
   String descricao,

   @NotNull(message="O id do cliente solicitante deve ser informado.")
   Long idCliente
) {}
