// empresa-form-component.component.ts
import { Component, Input, OnInit } from '@angular/core';
import { EmpresaService } from '../service/empresa.service';
import { Empresa } from '../dto/Empresa';

@Component({
  selector: 'app-empresa-form',
  templateUrl: './empresa-form.component.html',
  styleUrls: ['./empresa-form.component.css']
})
export class EmpresaFormComponent implements OnInit{

 
  rutValue: string = ''; 
  razonSocialValue: string = '';


  empresa: Empresa = {
    id:0,
    rut: '',
    razonSocial: '',
    fechaInsercion: ""    
  };
    empresas: any[] = [];
    showIdInput: boolean = false;

  constructor(private empresaService: EmpresaService) {}

  ngOnInit(): void {
    this.listarEmpresas();
  }
  
  toggleIdInput() {
    this.showIdInput = !this.showIdInput;
  }

  listarEmpresas() {
    this.empresaService.getEmpresa().subscribe(data => {
      this.empresas = data;
      console.log('dataempressa', this.empresas)
    });
  }


  createEmpresa() { 
    console.log('entra al metodo crear empresa');
    if (!this.empresa.rut || !this.empresa.razonSocial) {
      alert('Todos los campos son obligatorios');
      return; 
   }
    this.empresaService.createEmpresa(this.empresa).subscribe(response => {
      console.log('Empresa creada exitosamente', response);
      this.listarEmpresas();
    }, error => {
      console.error('Error al crear empresa', error);
    });
    this.empresa = {
      id:0,
      rut: '',
      razonSocial: '',
      fechaInsercion:''    
    };
  }

  updateEmpresa() { 
    console.log('entra al metodo update empresa' + this.rutValue);  
    console.log(this.empresa);
    if (confirm('¿Estás seguro de que deseas actualizar esta empresa?')) {
      if (!this.empresa.rut || !this.empresa.razonSocial ||!this.empresa.id) {
        alert('Todos los campos son obligatorios. Por favor, completa todos los campos');
            this.toggleIdInput();
        return; // Salir del método si algún campo está vacío
    }
        this.empresaService.updateEmpresa(this.empresa).subscribe(response => {
            console.log('empresa actualizado exitosamente', response);
            this.listarEmpresas();
            this.toggleIdInput();

           
        }, error => {
            console.error('Error al actualizar Empresa', error);
            alert('Error al actualizar Empresa')
        });
      
    }
    this.empresa = {
      id:0,
      rut: '',
      razonSocial: '',
      fechaInsercion:''    
    };
}
  
  deleteEmpresa(id: number) {
    this.listarEmpresas();
    this.empresaService.deleteEmpresa(id).subscribe(() => {
      this.listarEmpresas();
    }, error => {
      console.error('Error al eliminar la empresa', error);
    });
    this.listarEmpresas();
  }
  


  confirmDelete(id: number) {
    if (confirm('¿Estás seguro de que deseas eliminar esta empresa?')) {
      this.deleteEmpresa(id);
    }
    this.listarEmpresas();
  }
  
 
}
