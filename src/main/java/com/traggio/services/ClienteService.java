package com.traggio.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traggio.dtos.ClienteDto;
import com.traggio.models.Cliente;
import com.traggio.repositories.ClienteRepository;

@Service
public class ClienteService {

	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	@Autowired
	EmailService emailService;
	
	
	public Cliente findById(UUID id) {
		return clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Não foi possivel encontrar"));
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente createCliente(ClienteDto clienteDto) {
		var cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		emailService.enviarEmailTexto(cliente.getEmail(), "Nova Conta", "Sua conta foi criada com sucesso. Bem vindo ao Traggio " + cliente.getNome());
		return clienteRepository.save(cliente);
	}
	
	public Cliente uptadeCliente(UUID id, ClienteDto clienteDto) {
		var cliente =  findById(id);
		BeanUtils.copyProperties(clienteDto, cliente);
		return clienteRepository.save(cliente);
	}
	
	public void deleteCliente(UUID id) {
		var cliente = findById(id);
		clienteRepository.delete(cliente);
	}
	
}
