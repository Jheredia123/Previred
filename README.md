Proyecto Java 1.8 crud de empresa y trabajador

------------------------------------------------------------------------------------------------------------
Scrip BD
-- Crear el esquema "previred"

CREATE SCHEMA IF NOT EXISTS previred;

-- Crear la tabla "empresa" en el esquema "previred"

CREATE TABLE previred.empresa (
    id SERIAL PRIMARY KEY,
    rut VARCHAR(20),
    razon_social VARCHAR(100),
    fecha_insercion DATE,
    identificador_unico VARCHAR(50)
);


INSERT INTO previred.empresa (rut, razon_social, fecha_insercion, identificador_unico) 
VALUES ('11111111-1', 'Empresa de Prueba', '2024-02-13', 'ABC1234');



CREATE TABLE previred.trabajador (
    id SERIAL PRIMARY KEY,
    rut VARCHAR(20),
    nombre VARCHAR(50),
    apellido_paterno VARCHAR(50),
    apellido_materno VARCHAR(50),
    direccion_fisica VARCHAR(100),
    empresa_id INTEGER REFERENCES previred.empresa(id)
);

-- Insertar los datos de trabajador proporcionados en la tabla "trabajador"
INSERT INTO previred.trabajador (rut, nombre, apellido_paterno, apellido_materno, direccion_fisica, empresa_id) 
VALUES ('11111111-2', 'Juan', 'Perez', 'González', 'Calle 123', 1);

-------------------------------------------------------------------------------------------------------------------



Apis

Trabajadores

 trae a todos los trabajadores
Get http://localhost:8080/api/trabajadores 

 trae por Id 
Get http://localhost:8080/api/trabajadores/{id}

crea trabajadores
post http://localhost:8080/api/trabajadores/  

}
  "rut": "17770393-1",
  "nombre": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "González",
  "direccionFisica": "Calle 123",
  "empresa": {
    "id": 1,
   }
}

Actualiza
put  http://localhost:8080/api/trabajadores/{id}
        "rut": "17770393-1",
        "nombre": "Juan",
        "apellidoPaterno": "gonzalez",
        "apellidoMaterno": "González",
        "direccionFisica": "Calle 123",
         "empresa": 
            {
                "id": 4                
            }
    }

elimina
Delete http://localhost:8080/api/trabajadores/{id}

----------------------------------------------------------
Empresa

 (Trae todas las empresas)
Get http://localhost:8080/api/empresas

trae por id
Get http://localhost:8080/api/empresas/{id}

(Crea Empresas)
Post  http://localhost:8080/api/empresas/ 
Json{
        "rut": "11111111-1",
        "razonSocial": "Empresa de Prueba"    
    }


Actualiza
put http://localhost:8080/api/empresas/{id} 
{
    "rut": "11111111-1",
    "razonSocial": "Empresa de Prueba 4",
}

Elimina
Delete	http://localhost:8080/api/empresas/{1}

	
-------------------------------------------------------------------
