package com.javanauta.bff_agendadortarefas.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // ajuda a conversao dos dados

public class UsuarioDTOResponse {


    //Não expor dados e transformar em entidades


    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse> telefones;

}
