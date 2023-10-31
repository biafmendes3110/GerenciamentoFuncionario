package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Funcionario;
import com.projetojpa.services.FuncionarioServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Funcionario", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	private final FuncionarioServices funcionarioServices;

	@Autowired
	public FuncionarioController (FuncionarioServices funcionarioServices) {
		this.funcionarioServices = funcionarioServices;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza funcionario por ID")
	public ResponseEntity <Funcionario> buscaFuncionarioIdControlId(@PathVariable Long id){
		Funcionario funcionario = funcionarioServices.buscaFuncionarioId(id);
		if(funcionario!= null) {
			return ResponseEntity.ok(funcionario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os pedidos")
	public ResponseEntity<List<Funcionario>> buscaTodosFuncionarioControl() {
		List<Funcionario> Funcionario = funcionarioServices.buscaTodosFuncionarios();

		return ResponseEntity.ok(Funcionario);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastra um funcionario")
	public ResponseEntity<Funcionario> salvaFuncionarioControl(@RequestBody @Valid Funcionario funcionario){
		Funcionario salvaFuncionario = funcionarioServices.salvaFuncionario(funcionario);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);

	}
	@PutMapping ("/{id}")
	@Operation(summary = "altera as informações do id do funcionario")
	public ResponseEntity<Funcionario> alterarFuncionario(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
		Funcionario alterarFuncionario = funcionarioServices.alterarFuncionario(id,funcionario);
		if (alterarFuncionario  != null) {
			return ResponseEntity.ok(alterarFuncionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar o id selecionado")
	public ResponseEntity<String> apagaFuncionarioControl(@PathVariable Long id) {
		boolean apagar = funcionarioServices.apagarFuncionario(id);
		if(apagar) {
			return ResponseEntity.ok().body("O funcionário foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

