package org.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojagames.model.Usuario;
import org.generation.lojagames.model.UsuarioLogin;
import org.generation.lojagames.repository.UsuarioRepository;
import org.generation.lojagames.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
@Autowired
private UsuarioService service;

@Autowired
private UsuarioRepository usuarioRepository;

@GetMapping("/all")
public ResponseEntity<List<Usuario>> getAll() {
	return ResponseEntity.ok(usuarioRepository.findAll());
}

@PostMapping("/cadastrar")
public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
	return service.CadastrarUsuario(usuario).map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
}
@PostMapping("/logar")
public ResponseEntity<UsuarioLogin> Autentication(@Valid @RequestBody Optional<UsuarioLogin> user){
	return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
}

@PutMapping("/atualizar")
public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
	return service.atualizarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
}

}
