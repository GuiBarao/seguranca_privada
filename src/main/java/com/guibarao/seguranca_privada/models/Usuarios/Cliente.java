package com.guibarao.seguranca_privada.models;
import lombok.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente extends Usuario {
    private Plano plano;
    private List<Endereco> enderecos;
    private String telefone;
    private List<Parcela> parcelas;

}
