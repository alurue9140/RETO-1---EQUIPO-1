package Lector;
import Datos.*;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Lector {

    // Declaración de ArrayList para utilizar en el programa
    public ArrayList<Departamento> departamento = new ArrayList<>();
    public ArrayList <Empleado> empleado = new ArrayList<>();
    public ArrayList <Grupo> grupo = new ArrayList<>();
    public ArrayList <Empresa> empresa = new ArrayList<>();
    public ArrayList <Categoria> categoria = new ArrayList<>();
    public ArrayList <HorasExtra> horasExtra = new ArrayList<>();

    abstract void leer();
    public abstract void guardar(Lector lector) throws IOException;
}
