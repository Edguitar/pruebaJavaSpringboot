package com.prueba.java.services.impl;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.prueba.java.models.Cliente;
import com.prueba.java.models.Cuenta;
import com.prueba.java.repositories.ClienteRepository;
import com.prueba.java.services.ClienteServices;
@Service
public class ClienteServicesImpl implements ClienteServices {


	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarClientePorIdentificacion(String identificacion) {

		return clienteRepository.findByIdentificacion(identificacion)
				.orElseThrow(null);
	}

	@Override
	public Cliente ingresarCliente(Cliente cliente) {
		if (!clienteRepository.findByIdentificacion(cliente.getIdentificacion()).isPresent()) {
			for (Cuenta cuenta : cliente.getCuentas()) {
				String hideIdentificacion= java.util.Base64.getEncoder().encodeToString(cliente.getIdentificacion().getBytes());
				cliente.setIdentificacion(hideIdentificacion);
				cuenta.setCliente(cliente);
			}
			return clienteRepository.save(cliente);
		}
		return null;
	}

}
