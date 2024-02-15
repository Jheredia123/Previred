package com.previred.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.dto.EmpresaDto;
import com.previred.dto.TrabajadorDto;
import com.previred.entity.Empresa;
import com.previred.entity.Trabajador;
import com.previred.repository.TrabajadorRepository;
import com.previred.response.NoExisteResponseExeption;
import com.previred.response.RutInvalidoException;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

	@Autowired
	private TrabajadorRepository trabajadorRepository;

	@Override
	public List<TrabajadorDto> obtenerTodosLosTrabajadores() {
		List<Trabajador> trabajadores = trabajadorRepository.findAll();
		return trabajadores.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public TrabajadorDto obtenerTrabajadorDtoPorId(Long id) {
		Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);
		if (trabajador != null) {
			return convertToDto(trabajador);
		}
		throw new NoExisteResponseExeption("No existe Trabajador con el id ingresado");
	}

	@Override
	public TrabajadorDto crearTrabajador(TrabajadorDto trabajadorDto) {
		
		if (!validate(trabajadorDto.getRut())) {
			throw new RutInvalidoException("El RUT proporcionado no es válido");
		} else {
			Trabajador trabajador = convertToEntity(trabajadorDto);
			trabajador = trabajadorRepository.save(trabajador);
			return convertToDto(trabajador);
		}

	}

	@Override
	public TrabajadorDto actualizarTrabajador(TrabajadorDto trabajadorDto) {
		if (!validate(trabajadorDto.getRut())) {						
			throw new RutInvalidoException("El RUT proporcionado no es válido");
		}
		boolean existe = trabajadorRepository.existsById(trabajadorDto.getId());
		if(existe) {
			Trabajador	trabajador = convertToEntity(trabajadorDto);
			trabajador.setId(trabajadorDto.getId());
			trabajador = trabajadorRepository.save(trabajador);
			return convertToDto(trabajador);
				
		}else {
			throw new NoExisteResponseExeption("No existe Trabajador para eliminar");
		}
	
		
	}

	@Override
	public TrabajadorDto eliminarTrabajador(Long id) {
	    Optional<Trabajador> trabajadorOptional = trabajadorRepository.findById(id);
	    if (trabajadorOptional.isPresent()) {	    	
	        Trabajador trabajadorEliminado = trabajadorOptional.get();
	        trabajadorRepository.delete(trabajadorEliminado);
	        return convertToDto(trabajadorEliminado);
	    } else {
	    	throw new NoExisteResponseExeption("No existe Trabajador para eliminar");
	    }
	}
	
	

	private TrabajadorDto convertToDto(Trabajador trabajador) {
	    TrabajadorDto dto = new TrabajadorDto();
	    dto.setId(trabajador.getId());
	    dto.setRut(trabajador.getRut());
	    dto.setNombre(trabajador.getNombre());
	    dto.setApellidoPaterno(trabajador.getApellidoPaterno());
	    dto.setApellidoMaterno(trabajador.getApellidoMaterno());
	    dto.setDireccionFisica(trabajador.getDireccionFisica());

	    // Mapeo de la empresa
	    if (trabajador.getEmpresa() != null) {
	        Empresa empresa = trabajador.getEmpresa();
	        EmpresaDto empresaDto = new EmpresaDto();
	        empresaDto.setId(empresa.getId());
	        empresaDto.setRut(empresa.getRut());
	        empresaDto.setRazonSocial(empresa.getRazonSocial());
	        empresaDto.setFechaInsercion(empresa.getFechaInsercion());
	        empresaDto.setIdentificadorUnico(empresa.getIdentificadorUnico());
	        
	        // Asignar el objeto EmpresaDto al campo empresa en TrabajadorDto
	        dto.setEmpresa(empresaDto);
	    }

	    return dto;
	}
	
	private Trabajador convertToEntity(TrabajadorDto dto) {
	    Trabajador trabajador = new Trabajador();
	    trabajador.setRut(dto.getRut());
	    trabajador.setNombre(dto.getNombre());
	    trabajador.setApellidoPaterno(dto.getApellidoPaterno());
	    trabajador.setApellidoMaterno(dto.getApellidoMaterno());
	    trabajador.setDireccionFisica(dto.getDireccionFisica());

	    // Mapeo de la empresa
	    if (dto.getEmpresa() != null) {
	        EmpresaDto empresaDto = dto.getEmpresa();
	        Empresa empresa = new Empresa();
	        empresa.setId(empresaDto.getId());
	        empresa.setRut(empresaDto.getRut());
	        empresa.setRazonSocial(empresaDto.getRazonSocial());
	        empresa.setFechaInsercion(empresaDto.getFechaInsercion());
	        empresa.setIdentificadorUnico(empresaDto.getIdentificadorUnico());
	        trabajador.setEmpresa(empresa);
	    }

	    return trabajador;
	}
	public static boolean validate(String rut) {
	    if (rut == null || rut.isEmpty()) {
	        return false;
	    }

	    // Formato: 12345678-9 o 123456789
	    rut = rut.toUpperCase().replace(".", "").replace("-", "");

	    if (!rut.matches("\\d{7,8}[0-9kK]")) {
	        return false;
	    }

	    int rutDigits = Integer.parseInt(rut.substring(0, rut.length() - 1));
	    char verificationDigit = rut.charAt(rut.length() - 1);

	    int m = 2;
	    int sum = 0;
	    for (int i = rutDigits; i != 0; i /= 10) {
	        sum += (i % 10) * m;
	        m = m == 7 ? 2 : m + 1;
	    }

	    int expectedDigit = 11 - (sum % 11);
	    char computedVerificationDigit = (expectedDigit == 11) ? '0' : (expectedDigit == 10) ? 'K' : (char) (expectedDigit + '0');

	    return computedVerificationDigit == verificationDigit;
	}


}
