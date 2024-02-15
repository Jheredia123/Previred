package com.previred.service;

import java.util.List;

import com.previred.dto.TrabajadorDto;

public interface TrabajadorService {

    List<TrabajadorDto> obtenerTodosLosTrabajadores();

    TrabajadorDto obtenerTrabajadorDtoPorId(Long id);

    TrabajadorDto crearTrabajador(TrabajadorDto trabajadorDto);

    TrabajadorDto actualizarTrabajador(TrabajadorDto trabajadorDto);

    TrabajadorDto eliminarTrabajador(Long id);
}
