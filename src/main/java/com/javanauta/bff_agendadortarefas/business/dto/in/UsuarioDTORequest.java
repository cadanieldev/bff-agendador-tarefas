package com.javanauta.bff_agendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // ajuda a conversao dos dados

public class UsuarioDTORequest {


    //Não expor dados e transformar em entidades


    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTORequest> telefones;

}
