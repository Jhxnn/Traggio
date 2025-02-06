package com.traggio.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traggio.dtos.AuthDto;
import com.traggio.dtos.ClienteDto;
import com.traggio.infra.security.TokenService;
import com.traggio.models.Cliente;
import com.traggio.repositories.ClienteRepository;
import com.traggio.services.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	EmailService emailService;	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Operation(description = "Realiza processo de login do usuario")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthDto authDto) {
		var usernamePassord = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
		var auth = this.authenticationManager.authenticate(usernamePassord);
		var token = tokenService.generateToken((Cliente) auth.getPrincipal());
		return ResponseEntity.ok().body(token);
	}
	
	@Operation(description = "Registra um usuario")
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid  ClienteDto clienteDto) {
		
		if(clienteRepository.findByEmail(clienteDto.email()) != null) return ResponseEntity.badRequest().body("Email já usado!");
		
		String encryptedPass = new BCryptPasswordEncoder().encode(clienteDto.senha());
		var cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		cliente.setSenha(encryptedPass);
		emailService.enviarEmailTexto(cliente.getEmail(), "Conta Criada - TRAGGIO", "Sua conta foi criada com sucesso. \nBem vindo ao Traggio, " + cliente.getNome() + ". \nChave de de verificação: 443. \nQualquer duvida contate nosso suporte! ");
		clienteRepository.save(cliente);
		return ResponseEntity.ok().body("Conta criada! \nemail: " + cliente.getEmail());
		
	}
}
