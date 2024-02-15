package com.previred.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.dto.EmpresaDto;
import com.previred.response.ErrorResponse;
import com.previred.response.NoExisteResponseExeption;
import com.previred.service.EmpresaService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/api/empresas")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;	
	
	@GetMapping
	public List<EmpresaDto> obtenerTodasLasEmpresas() {
		return empresaService.obtenerTodasLasEmpresas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerEmpresaPorId(@PathVariable Long id) {
		try {
			EmpresaDto empresaDto = empresaService.obtenerEmpresaPorId(id);
			return new ResponseEntity<>(empresaDto, HttpStatus.OK);
		} catch (NoExisteResponseExeption e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

	}

	@PostMapping
	public ResponseEntity<EmpresaDto> crearEmpresa(@RequestBody EmpresaDto empresaDto) {
		empresaDto = empresaService.crearEmpresa(empresaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDto empresaDto) {
		try {
			empresaDto.setId(id);
			EmpresaDto empresaActualizada = empresaService.actualizarEmpresa(empresaDto);
			return ResponseEntity.ok(empresaActualizada);
		} catch (NoExisteResponseExeption e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id) {
		try {
			boolean eliminado = empresaService.eliminarEmpresa(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(id + " Empresa eliminada");
		} catch (NoExisteResponseExeption e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

	}
}
