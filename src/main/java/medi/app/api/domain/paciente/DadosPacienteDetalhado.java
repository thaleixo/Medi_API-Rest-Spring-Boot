package medi.app.api.domain.paciente;

import medi.app.api.domain.medico.Endereco;

public record DadosPacienteDetalhado(long id, String nome, String email, String telefone , String cpf, Endereco endereco) {

    public DadosPacienteDetalhado(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
