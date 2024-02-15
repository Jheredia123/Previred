import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { TrabajadorFormComponent } from './trabajador-form/trabajador-form.component';
import { EmpresaFormComponent } from './empresa-form/empresa-form.component';

@NgModule({
  declarations: [
    AppComponent,
    EmpresaFormComponent,
    TrabajadorFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
    ],  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
