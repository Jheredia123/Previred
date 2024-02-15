import { Component, Input, OnInit } from '@angular/core';
import { TrabajadorService } from '../service/trabajador.service';
import {Trabajador} from '../dto/Trabajador';

@Component({
  selector: 'app-trabajador-form',
  templateUrl: './trabajador-form.component.html',
  styleUrls: ['./trabajador-form.component.css']
})
export class TrabajadorFormComponent implements OnInit{

  rutValue: string = ''; 
  nombre: string = '';

  trabajador: Trabajador = {
    id:0,
    rut: '',
    nombre: '',
    apellidoPaterno: '',
    apellidoMaterno: '',
    direccionFisica: '',
    empresa: {
      id: 0
    }
  };

  trabajadores: Trabajador[] = [];
  showIdInput: boolean = false;
 


  constructor(private trabajadorservice: TrabajadorService) {}

  ngOnInit(): void {
    this.listarTrabajadores();
  }
  
  listarTrabajadores() {
    this.trabajadorservice.getTrabajadores().subscribe(data => {
      this.trabajadores = data;
      console.log('dataTrabajadores', this.trabajadores)
    });
  }


  createTrabajador() { 
    console.log('entra al metodo crear trabajador' + this.rutValue);
  
    console.log(this.trabajador);
    this.trabajadorservice.createTrabajadores(this.trabajador).subscribe(response => {
      console.log('trabajador creado exitosamente', response);
      this.listarTrabajadores();
    }, error => {
      console.error('Error al crear empresa', error);
    });
    this.trabajador = {
      id:0,
      rut: '',
      nombre: '',
      apellidoPaterno: '',
      apellidoMaterno: '',
      direccionFisica: '',
      empresa: {
        id: 0
      }
    };
  }

  toggleIdInput() {
    this.showIdInput = !this.showIdInput;
  }

  updateTrabajador() { 
    console.log('entra al metodo crear trabajador' + this.rutValue);  
    console.log(this.trabajador);
    if (confirm('¿Estás seguro de que deseas eliminar al trabajador?')) {
      if (!this.trabajador.rut || !this.trabajador.nombre || !this.trabajador.apellidoPaterno || !this.trabajador.apellidoMaterno || !this.trabajador.direccionFisica || !this.trabajador.empresa.id) {
        alert('Todos los campos son obligatorios. Por favor, completa todos los campos antes de actualizar.');
            this.toggleIdInput();
        return; // Salir del método si algún campo está vacío
    }
        this.trabajadorservice.updateTrabajadores(this.trabajador).subscribe(response => {
            console.log('trabajador actualizado exitosamente', response);
            this.listarTrabajadores();
            this.toggleIdInput();

           
        }, error => {
            console.error('Error al actualizar trabajador', error);
            alert('Error al actualizar trabajador')
        });
      
    }

    this.trabajador = {
      id:0,
      rut: '',
      nombre: '',
      apellidoPaterno: '',
      apellidoMaterno: '',
      direccionFisica: '',
      empresa: {
        id: 0
      }
    };
}
  
  delete(id: number) {
    this.listarTrabajadores();
    this.trabajadorservice.deleteTrabajadores(id).subscribe(() => {
      this.listarTrabajadores();
    }, error => {
      console.error('Error al eliminar la trabajador', error);
    });
    this.listarTrabajadores();
  }
  


  confirmDelete(id: number) {
    if (confirm('¿Estás seguro de que deseas eliminar esta trabajador?')) {
      this.delete(id);
    }
    this.listarTrabajadores();
  }
  


}
