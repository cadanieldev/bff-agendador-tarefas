package com.javanauta.bff_agendadortarefas.business;


import com.javanauta.bff_agendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.javanauta.bff_agendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioClient client;
    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTOResponse) {
        return client.salvaUsuario(usuarioDTOResponse);
    }


    public String loginUsuario(LoginRequestDTO usuarioDTOResponse){
        return client.login(usuarioDTOResponse);
    }



    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
       return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    //Ternario e put
    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) { // verificar se existe, se nao pegar na entity
       return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTOResponse, String token) {
        return client.atualizaEndereco(enderecoDTOResponse, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {
        return client.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
       return client.cadastraEndereco(dto,token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
       return client.cadastraTelefone(dto, token);
    }

    public ViaCepDTOResponse buscaEnderecoPorCep(String cep){
        return client.buscarDadosCep(cep);
    }

}
