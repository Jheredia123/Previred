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

import com.previred.dto.TrabajadorDto;
import com.previred.response.ErrorResponse;
import com.previred.response.NoExisteResponseExeption;
import com.previred.response.RutInvalidoException;
import com.previred.service.TrabajadorService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;

	@GetMapping
	public List<TrabajadorDto> obtenerTodosLosTrabajadores() {
		return trabajadorService.obtenerTodosLosTrabajadores();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TrabajadorDto> obtenerTrabajadorPorId(@PathVariable Long id) {
		TrabajadorDto trabajadorDto = trabajadorService.obtenerTrabajadorDtoPorId(id);
		if (trabajadorDto != null) {
			return ResponseEntity.ok(trabajadorDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/creaTrabajador")
	public ResponseEntity<?> crearTrabajador(@RequestBody TrabajadorDto trabajadorDto) {
		try {
			TrabajadorDto nuevoTrabajadorDto = trabajadorService.crearTrabajador(trabajadorDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTrabajadorDto);
		} catch (RutInvalidoException e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarTrabajador(@PathVariable Long id, @RequestBody TrabajadorDto trabajadorDto) {
		trabajadorDto.setId(id);
		try {
			TrabajadorDto trabajadorActualizadoDto = trabajadorService.actualizarTrabajador(trabajadorDto);

			return ResponseEntity.ok(trabajadorActualizadoDto);

		} catch (RutInvalidoException e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (NoExisteResponseExeption e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarTrabajador(@PathVariable Long id) {
		try {
			TrabajadorDto trabajadorEliminadoDto = trabajadorService.eliminarTrabajador(id);
			return ResponseEntity.ok(trabajadorEliminadoDto);
		} catch (NoExisteResponseExeption e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

	}

}
