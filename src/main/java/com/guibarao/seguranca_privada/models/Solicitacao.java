package com.guibarao.seguranca_privada.models;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Administrador;
import lombok.*;
import com.guibarao.seguranca_privada.models.StatusSolicitacoes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {
    private Long id;
    private String decricao;
    private StatusSolicitacoes status;
    private Cliente solicitante;
    private Administrador atendente;

}