Arquetipos de Orientacion a Objetos
* Ejercicio 6:

6.1 Empleados
Este código muestra tres clases: EmpleadoTemporario, EmpleadoPlanta y EmpleadoPasante.

- Iteración 1:

(i) Mal olor: Código Duplicado (Duplicated Code) y Romper Encapsulamiento. Los atributos nombre, apellido y sueldoBasico están repetidos en las tres clases y declarados como public. Además, el método sueldo() tiene firmas idénticas.
(ii) Refactoring: Extract Superclass (Extraer Superclase) para agrupar los atributos comunes y Encapsulate Field para hacerlos privados/protegidos.
(iii) Aplicación: Creamos la superclase Empleado.

- Iteración 2:
(i) Mal olor: Código Duplicado. El método sueldo() hace un cálculo similar en todos lados pero difiere en partes específicas.
(ii) Refactoring: Pull Up Method (para subir sueldo()) combinado con el patrón Template Method (Form Template Method, p.345 en Fowler). Separamos la parte constante de la variable.