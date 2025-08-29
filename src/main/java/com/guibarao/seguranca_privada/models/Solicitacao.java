package com.guibarao.seguranca_privada.models;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Funcionario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {
    private Long id;
    private String decricao;
    private StatusSolicitacoes status;
    private Cliente solicitante;
    private Funcionario atendente;

}