package com.previred.service;

import java.util.List;

import com.previred.dto.EmpresaDto;
import com.previred.entity.Empresa;

public interface EmpresaService {
	List<EmpresaDto> obtenerTodasLasEmpresas();
	    EmpresaDto obtenerEmpresaPorId(Long id);
	    EmpresaDto crearEmpresa(EmpresaDto empresa);
	    EmpresaDto actualizarEmpresa(EmpresaDto empresa);
	    boolean eliminarEmpresa(Long id);

}
