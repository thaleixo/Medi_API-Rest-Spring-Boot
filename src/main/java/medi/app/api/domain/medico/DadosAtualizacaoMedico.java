package medi.app.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import medi.app.api.domain.endereco.Dadosendereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Dadosendereco dadosendereco){
}
