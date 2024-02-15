package com.previred.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.dto.EmpresaDto;
import com.previred.dto.TrabajadorDto;
import com.previred.entity.Empresa;
import com.previred.entity.Trabajador;
import com.previred.repository.EmpresaRepository;
import com.previred.response.NoExisteResponseExeption;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired(required = true)
	private EmpresaRepository empresaRepository;




	@Override
	public List<EmpresaDto> obtenerTodasLasEmpresas() {
		List<Empresa> empresas = empresaRepository.findAll();
		return empresas.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public EmpresaDto obtenerEmpresaPorId(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			Empresa empresa = empresaOptional.get();
			return convertToDto(empresa);
		}
		throw new NoExisteResponseExeption("El id de la empresa no exite en la base de datos");
	}

	@Override
	public EmpresaDto crearEmpresa(EmpresaDto empresaDto) {
			
			Empresa empresa = convertToEntity(empresaDto);
			empresa.setIdentificadorUnico(generate());
			Date fechaActual = new Date();
			empresa.setFechaInsercion(fechaActual);
			empresa = empresaRepository.save(empresa);
		return convertToDto(empresa);
	}

	@Override
	public EmpresaDto actualizarEmpresa(EmpresaDto empresaDto) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(empresaDto.getId());
		
		if (empresaOptional.isPresent()) {
			Empresa empresa = convertToEntity(empresaDto);
			empresa.setId(empresaDto.getId());
			empresa.setIdentificadorUnico(generate());
			Date fechaActual = new Date();
			empresa.setFechaInsercion(fechaActual);
			empresa = empresaRepository.save(empresa);
			return convertToDto(empresa);
		} else {
			throw new NoExisteResponseExeption("No existe Empresa para actualizar");
		}
	}

	@Override
	public boolean eliminarEmpresa(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			Date fechaActual = new Date();
			empresaRepository.delete(empresaOptional.get());
			return true;
		}
		throw new NoExisteResponseExeption("No existe Empresa para eliminar");
	}

	private EmpresaDto convertToDto(Empresa empresa) {
		EmpresaDto dto = new EmpresaDto();
		dto.setId(empresa.getId());
		dto.setRut(empresa.getRut());
		dto.setRazonSocial(empresa.getRazonSocial());
		dto.setFechaInsercion(empresa.getFechaInsercion());
		dto.setIdentificadorUnico(empresa.getIdentificadorUnico());

		List<TrabajadorDto> trabajadoresDto = empresa.getTrabajadores().stream().map(this::convertTrabajadorToDto)
				.collect(Collectors.toList());
		dto.setTrabajadores(trabajadoresDto);

		return dto;
	}

	private TrabajadorDto convertTrabajadorToDto(Trabajador trabajador) {
		TrabajadorDto trabajadorDto = new TrabajadorDto();
		trabajadorDto.setId(trabajador.getId());
		trabajadorDto.setRut(trabajador.getRut());
		trabajadorDto.setNombre(trabajador.getNombre());
		trabajadorDto.setApellidoPaterno(trabajador.getApellidoPaterno());
		trabajadorDto.setApellidoMaterno(trabajador.getApellidoMaterno());
		trabajadorDto.setDireccionFisica(trabajador.getDireccionFisica());

		return trabajadorDto;
	}

	private Empresa convertToEntity(EmpresaDto dto) {
		Empresa empresa = new Empresa();
		empresa.setRut(dto.getRut());
		empresa.setRazonSocial(dto.getRazonSocial());
		empresa.setFechaInsercion(dto.getFechaInsercion());
		empresa.setIdentificadorUnico(dto.getIdentificadorUnico());
		// No se incluyen trabajadores aquí, ya que estos no se manejan directamente en
		// los métodos del servicio
		return empresa;
	}
	

    
	  public static String generate() {
	        LocalDateTime now = LocalDateTime.now();
	        StringBuilder identifier = new StringBuilder();

	        identifier.append(now.getYear())
	                  .append(String.format("%02d", now.getMonthValue()))
	                  .append(String.format("%02d", now.getDayOfMonth()))
	                  .append(String.format("%02d", now.getHour()))
	                  .append(String.format("%02d", now.getMinute()))
	                  .append(String.format("%02d", now.getSecond()));

	        Random random = new Random();
	        int remainingLength = 50 - identifier.length(); 
	        for (int i = 0; i < remainingLength; i++) {
	            char randomChar = (char) (random.nextInt(26) + 'a');
	            identifier.append(randomChar);
	        }

	        return identifier.toString();
	    }
	
}
