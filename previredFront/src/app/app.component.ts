import { Component } from '@angular/core';
import { EmpresaService } from './service/empresa.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'previred';

constructor(private empresaService: EmpresaService) { }



empresaFormEnabled: boolean = false;
trabajadorFormEnabled: boolean = false;

toggleEmpresaForm() {
  this.empresaFormEnabled = !this.empresaFormEnabled;
  this.trabajadorFormEnabled = false; // Ocultar el formulario de trabajador al mostrar el de empresa
}

toggleTrabajadorForm() {
  this.trabajadorFormEnabled = !this.trabajadorFormEnabled;
  this.empresaFormEnabled = false; // Ocultar el formulario de empresa al mostrar el de trabajador
}

}
