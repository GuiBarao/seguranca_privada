package com.guibarao.seguranca_privada.models;
import com.guibarao.seguranca_privada.models.Usuarios.Cliente;
import com.guibarao.seguranca_privada.models.Usuarios.Funcionario;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {
    private Long id;
    private String descricao;
    private StatusSolicitacoes status;
    private Cliente solicitante;
    private Long idSolicitante;
    private Funcionario atendente;
    private Long idAtendente;
    private LocalDateTime solicitado_em;


    public Solicitacao(Long id, String descricao, StatusSolicitacoes status, Cliente solicitante, Funcionario atendente) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.solicitante = solicitante;
        this.atendente = atendente;


    }

}