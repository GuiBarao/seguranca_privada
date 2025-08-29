package com.guibarao.seguranca_privada.models.Usuarios;
import com.guibarao.seguranca_privada.models.Endereco;
import com.guibarao.seguranca_privada.models.Parcela;
import com.guibarao.seguranca_privada.models.Plano;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Cliente extends Usuario {
    private Plano plano;
    private Long idPlano;
    private List<Endereco> enderecos = new ArrayList<Endereco>();
    private String telefone;
    private List<Parcela> parcelas = new ArrayList<Parcela>();

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.CLIENTE;
    }


}
