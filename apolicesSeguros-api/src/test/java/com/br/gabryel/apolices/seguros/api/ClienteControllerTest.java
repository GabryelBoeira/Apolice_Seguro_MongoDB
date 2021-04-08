package com.br.gabryel.apolices.seguros.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.gabryel.apolices.seguros.api.model.Cliente;
import com.br.gabryel.apolices.seguros.api.repository.ClienteRepository;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class ClienteControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ClienteRepository clienteRepository;

	private Cliente cliente;

	@BeforeEach
	public void start() throws Exception {

		cliente = new Cliente("Teste", "41", "Curitiba", "PR");
		clienteRepository.save(cliente);
	}

	@AfterEach
	public void stop() {		
		clienteRepository.deleteAll();		
	}

	@Test
	void testCreateCliente()  throws Exception  {
		
		ResponseEntity<List<Cliente>> resposta = testRestTemplate.exchange("/cliente/list-all", HttpMethod.GET, null,  new ParameterizedTypeReference<List<Cliente>>() {});
		
		assertEquals(1, resposta.getBody().size());		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
