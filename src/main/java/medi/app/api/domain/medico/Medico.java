package medi.app.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;

@Table(name= "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id" )
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(Dadosmedico dadosmedico) {
        this.nome = dadosmedico.nome();
        this.email = dadosmedico.email();
        this.crm = dadosmedico.crm();
        this.telefone = dadosmedico.telefone();
        this.especialidade = dadosmedico.especialidade();
        this.endereco = new Endereco(dadosmedico.endereco());
        this.ativo = true;
    }


    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.dadosendereco() != null){
            this.endereco.atualizarEndereco(dados.dadosendereco());
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
