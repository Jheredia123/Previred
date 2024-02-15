package com.previred.dto;

import java.util.Date;
import java.util.List;

public class EmpresaDto {
    private Long id;
    private String rut;
    private String razonSocial;
    private Date fechaInsercion;
    public Date getFechaInsercion() {
		return fechaInsercion;
	}

	public void setFechaInsercion(Date fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}

	private String identificadorUnico;
    private List<TrabajadorDto> trabajadores;

    // Constructor
    public EmpresaDto() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

  
    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    public void setIdentificadorUnico(String identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }

    public List<TrabajadorDto> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<TrabajadorDto> trabajadores) {
        this.trabajadores = trabajadores;
    }
}