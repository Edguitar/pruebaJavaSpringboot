package com.prueba.java.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.java.models.Cliente;


@Service
public interface ClienteServices  {

List<Cliente> obtenerClientes();
Cliente buscarClientePorIdentificacion(String identificacion);
Cliente ingresarCliente(Cliente cliente);

}
