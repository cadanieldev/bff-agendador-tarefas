package com.javanauta.bff_agendadortarefas.controller;

import com.javanauta.bff_agendadortarefas.business.UsuarioService;
import com.javanauta.bff_agendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendadortarefas.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro de login e usuários")// nome da controller e descricao

public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping // nao tem token pq ele precisa queo usuario seja criado antes
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "409", description = "Usuário já cadástrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTOResponse) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTOResponse));
    }

    //login
    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuario") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTOResponse) {
       return usuarioService.loginUsuario(usuarioDTOResponse);
    }

    @GetMapping // anotacao metodo get
    @Operation(summary = "Buscar dados de Usuários por Email", description = "Buscar dados do usuario") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Usuário encontado")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token) { //
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));

    } // Metodo get pronto buscando o email com o find e optional de exception

    @DeleteMapping("/{email}") // metodo delete com aspas e chaves
    @Operation(summary = "Deleta Usuários por Id", description = "Deleta Usuários") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Usuário deletado")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build(); // retorna algum erro
    }


    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuários", description = "Atualizar dados de usuário") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Endereco de Usuários", description = "Atualiza endereço de Usuario") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de Usuários", description = "Atualiza telefone de Usuário") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário", description = "Salva Endereço de Usuário") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuário", description = "Salva Telefone de Usuário") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereço pelo cep", description = "Busca dados de endereço recebendo um cep") // Descricao do metodo
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com sucesso")  //Indicar os status codes
    @ApiResponse(responseCode = "400", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable ("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscaEnderecoPorCep(cep));
    }

}
