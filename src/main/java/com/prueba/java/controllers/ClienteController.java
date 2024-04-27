package com.prueba.java.controllers;

import java.util.Base64;
import java.util.List;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.java.models.Cliente;
import com.prueba.java.services.ClienteServices;
import com.prueba.java.utils.WebUtil;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController{

	@Autowired
	private ClienteServices clienteServices;

  	@GetMapping()
	public ResponseEntity<?>obtener(){
	  List<Cliente> clientes = clienteServices.obtenerClientes();
	  if (clientes==null||clientes.isEmpty()) {
		  return ResponseEntity.noContent().build();
	  }
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	  @GetMapping("/identificacion")
	    public ResponseEntity<?> buscarClientePorIdentificacion(@PathVariable String identificacion) {
	        Cliente cliente = clienteServices.buscarClientePorIdentificacion(identificacion);
	        if (cliente != null) {
	            return ResponseEntity.ok(cliente);
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra cliente");
	        }
	    }
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody @Validated Cliente cliente, BindingResult result) {
		if(result.hasErrors()){
			return WebUtil.getErrors(result);
		}		
		
		Cliente nuevoCliente= clienteServices.ingresarCliente(cliente);
		if(nuevoCliente==null) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra cliente");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}
}
