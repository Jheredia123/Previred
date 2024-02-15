export interface Trabajador {
  id: number;
  rut: string;
  nombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  direccionFisica: string;
  empresa: {
    id: number;
  };
}