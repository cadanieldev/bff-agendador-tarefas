package com.javanauta.bff_agendadortarefas.infrastructure.client;

import com.javanauta.bff_agendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}") // local para armazenar url e adicionar na properties
public interface UsuarioClient {

    //Apontar para a uri que vai trazer os dados do usuario
    @GetMapping("/usuario")
    // sempre apontar o URI no get
    // anotacao metodo get
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
        // nao tem token pq ele precisa queo usuario seja criado antes
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTOResponse);


    //login
    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTOResponse);

    @DeleteMapping("/{email}")
        // metodo delete com aspas e chaves
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);


    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);


    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);


    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);


}
