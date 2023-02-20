package Lector;

import Datos.*;

import java.io.*;

/**
 * @author Jorge Pitarch
 */

public class LectorCSV extends Lector {

    /**
     * autor Isaac Rueda
     * colaborador George Popa
     */

    // Declaración de variables para utilizarlas en el método

    public  final String SEPARADOR = ";" ;
    public  final String RUTA = "./../../recursos/";

    // Método para leer CSV

    public void leer() {

        /**
         * autor Isaac Rueda
         * colaborador George Popa
         */

        // Abrir todos los lectores de archivos

        BufferedReader bufferLecturaHorasExtra = null;
        BufferedReader bufferLecturaDepartamentos = null;
        BufferedReader bufferLecturaEmpleados = null;
        BufferedReader bufferLecturaEmpresa = null;
        BufferedReader bufferLecturaGrupos = null;
        BufferedReader bufferLecturaCategorias = null;

        try {

            // Abrir el .csv en buffer de lectura

            bufferLecturaCategorias = new BufferedReader(new FileReader("/home/INFORMATICA/alu10217602/Escriptori/RETO-1---EQUIPO-1-master/RETO-1/recursos/Categorias.csv"
            ));
            bufferLecturaEmpresa = new BufferedReader(new FileReader(RUTA + "DatosEmpresa.csv"));
            bufferLecturaDepartamentos = new BufferedReader(new FileReader(RUTA + "Departamentos.csv"));
            bufferLecturaEmpleados = new BufferedReader(new FileReader(RUTA + "Empleados.csv"));
            bufferLecturaGrupos= new BufferedReader(new FileReader(RUTA + "Grupos.csv"));
            bufferLecturaHorasExtra = new BufferedReader(new FileReader(RUTA + "Datos.HorasExtra.csv"));

            // Leer una línea del archivo

            String lineaCategorias = bufferLecturaCategorias.readLine();
            String lineaEmpresa = bufferLecturaEmpresa.readLine();
            String lineaDepartamentos = bufferLecturaDepartamentos.readLine();
            String lineaEmpleados = bufferLecturaEmpleados.readLine();
            String lineaGrupos = bufferLecturaGrupos.readLine();
            String lineaHorasExtra = bufferLecturaHorasExtra.readLine();

            // Leer la segunda línea, para saltarnos la cabecera del .csv

            lineaDepartamentos = bufferLecturaDepartamentos.readLine();
            lineaEmpresa = bufferLecturaEmpresa.readLine();
            lineaEmpleados = bufferLecturaEmpleados.readLine();
            lineaCategorias = bufferLecturaCategorias.readLine();
            lineaGrupos = bufferLecturaGrupos.readLine();
            lineaHorasExtra = bufferLecturaHorasExtra.readLine();

            /**
             * Como sabemos la estructura de cada CSV, guardamos cada línea en un array de Strings y después
             * con el constructor de cada Clase, añadimos cada elemento del array a una nueva instancia/objeto de
             * dicha clase, pasándolo de String al valor que haga falta en cada momento. Por último se añade ese objeto
             * a su ArrayList correspondiente y se lee/carga la siguiente línea del fichero .csv
             */
            Departamento departamento = null;
            Empresa empresa = null;
            Empleado empleado = null;
            Categoria categoria = null;
            Grupo grupo = null;
            HorasExtra horasExtra = null;

            rellenarDatos(bufferLecturaDepartamentos, lineaDepartamentos, departamento);
            rellenarDatos(bufferLecturaEmpresa, lineaEmpresa, empresa);
            rellenarDatos(bufferLecturaEmpleados, lineaEmpleados, empleado);
            rellenarDatos(bufferLecturaCategorias, lineaCategorias, categoria);
            rellenarDatos(bufferLecturaGrupos, lineaGrupos, grupo);
            rellenarDatos(bufferLecturaHorasExtra, lineaHorasExtra, horasExtra);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rellenarDatos(BufferedReader bufferLectura, String linea, Datos datos) throws IOException {
        while (linea != null) {

            String [] dato = linea.split(SEPARADOR);

            if (datos instanceof Departamento) {

                Departamento nuevo = new Departamento(Integer.parseInt(dato[0]), dato[1]);
                this.departamento.add(nuevo);

            } else if (datos instanceof Empresa) {

                Empresa nuevo = new Empresa(Integer.parseInt(dato[0]), dato[1],dato[2],Integer.parseInt(dato[3]),Integer.parseInt(dato[4]),dato[5]);
                this.empresa.add(nuevo);

            } else if (datos instanceof Empleado) {

                Empleado nuevo = new Empleado(Integer.parseInt(dato[0]), dato[1], dato[2],dato[3],dato[4],dato[5],dato[6],dato[7],Integer.parseInt(dato[8]));
                this.empleado.add(nuevo);

            } else if (datos instanceof Categoria) {

                Categoria nuevo = new Categoria (dato[0]);
                this.categoria.add(nuevo);

            } else if (datos instanceof Grupo) {

                Grupo nuevo = new Grupo((dato[0]), dato[1], dato[2],Double.parseDouble(dato[3]));
                this.grupo.add(nuevo);

            } else if (datos instanceof HorasExtra) {

                HorasExtra nuevo = new HorasExtra (Integer.parseInt(dato[0]), dato[1], Integer.parseInt(dato[2]));
                this.horasExtra.add(nuevo);

            }

            linea = bufferLectura.readLine();

        }
        bufferLectura.close();
    }

    public void guardar(Lector l) throws IOException {

        /**
         * autor Jorge Pitarch
         */

        BufferedWriter escrituraCategorias = new BufferedWriter(new FileWriter(RUTA + "Categorias.csv"));
        BufferedWriter escrituraDatosEmpresa = new BufferedWriter(new FileWriter(RUTA + "DatosEmpresa.csv"));
        BufferedWriter escrituraDepartamentos = new BufferedWriter(new FileWriter(RUTA + "Departamentos.csv"));
        BufferedWriter escrituraEmpleados = new BufferedWriter(new FileWriter(RUTA + "Empleados.csv"));
        BufferedWriter escrituraGrupos = new BufferedWriter(new FileWriter(RUTA + "Grupos.csv"));
        BufferedWriter escrituraHorasExtra = new BufferedWriter(new FileWriter(RUTA + "Datos.HorasExtra.csv"));

        escrituraCategorias.write("Cod_cat");
        for (Categoria cat : l.categoria){
            escrituraCategorias.newLine();
            escrituraCategorias.write(cat.toStringCSV());
        }

        escrituraDatosEmpresa.write("ID;cargo;antiguedad;cod_d;cod_g;cod_c");
        for (Empresa e : l.empresa){
            escrituraDatosEmpresa.newLine();
            escrituraDatosEmpresa.write(e.toStringCSV());
        }

        escrituraDepartamentos.write("codi;nombre");
        for (Departamento dept : l.departamento){
            escrituraDepartamentos.newLine();
            escrituraDepartamentos.write(dept.toStringCSV());
        }

        escrituraEmpleados.write("ID;nif;Nombre;Apellido1;Apellido2;cuenta;N__de_afiliacion_a_la_Seguridad_Social;email;departamento");
        for (Empleado e : l.empleado){
            escrituraEmpleados.newLine();
            escrituraEmpleados.write(e.toStringCSV());
        }

        escrituraGrupos.write("cod_g;Nombre;Formacion;SalarioBase");
        for (Grupo grupo : l.grupo){
            escrituraGrupos.newLine();
            escrituraGrupos.write(grupo.toStringCSV());
        }

        escrituraHorasExtra.write("ID;fecha;horas");
        for (HorasExtra horas : l.horasExtra){
            escrituraHorasExtra.newLine();
            escrituraHorasExtra.write(horas.toStringCSV());
        }

        escrituraCategorias.close();
        escrituraDatosEmpresa.close();
        escrituraDepartamentos.close();
        escrituraEmpleados.close();
        escrituraGrupos.close();
        escrituraHorasExtra.close();
    }

}
