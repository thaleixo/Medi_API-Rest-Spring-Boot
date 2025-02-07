package medi.app.api.domain.medico;

public record DadosMedicoDetalhados(Long id,String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosMedicoDetalhados(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }


}
