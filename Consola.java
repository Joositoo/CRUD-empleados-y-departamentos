package org.example.BDEmpleados;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Consola {
    static Scanner scann = new Scanner(System.in);

    public static void menu() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          MAIN MENU              #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Trabajar con departamentos  #");
        System.out.println("#  2. Trabajar con empleados      #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-2): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                menuDepartamentos();
                break;

            case 2:
                menuEmpleados();
                break;

            default:
                menu();
                break;
        }
    }

    public static void menuDepartamentos() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          DEPARTAMENTOS          #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Mostrar departamentos       #");
        System.out.println("#  2. Seleccionar departamento    #");
        System.out.println("#  3. Añadir departamento         #");
        System.out.println("#  4. Modificar departamento      #");
        System.out.println("#  5. Eliminar departamento       #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-5): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                mostrarDepartamentos();
                break;
            case 2:
                menuSeleccionarDpto();
                break;
            case 3:
                añadirDepartamento();
                mostrarDepartamentos();
                break;
            case 4:
                modDpto();
                mostrarDepartamentos();
                break;
            case 5:
                menuEliminarDpto();
                mostrarDepartamentos();
                break;
            default:
                menuDepartamentos();
                break;
        }
    }

    public static void mostrarDepartamentos() throws SQLException {
        DAODepartamento departamento = new DAODepartamento();
        ArrayList<DTODepartamento> listaDptos;

        listaDptos = departamento.selectAllDepartamentos();

        for (DTODepartamento dpto : listaDptos){
            System.out.println(dpto.toString());
        }
    }

    public static void menuSeleccionarDpto() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          DEPARTAMENTOS          #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Seleccionar por id          #");
        System.out.println("#  2. Seleccionar por nombre      #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-2): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                System.out.println(seleccionaDptoPorID());
                break;
            case 2:
                System.out.println(seleccionaDptoPorNombre());
                break;
            default:
                menuSeleccionarDpto();
                break;
        }
    }

    public static DTODepartamento seleccionaDptoPorID() throws SQLException {
        System.out.print("Indica el id del departamento: ");
        int id = scann.nextInt();

        DAODepartamento daoDepartamento = new DAODepartamento();

        return daoDepartamento.selectDepartamentoById(id);
    }

    public static DTODepartamento seleccionaDptoPorNombre() throws SQLException {
        System.out.print("Indica el nombre del departamento: ");
        scann.nextLine(); // Consumir el carácter de nueva línea pendiente
        String nombre = scann.nextLine();

        DAODepartamento daoDepartamento = new DAODepartamento();

        return daoDepartamento.selectDepartamentoByName(nombre);
    }

    public static void añadirDepartamento() throws SQLException {
        System.out.print("Introduce el id del departamento: ");
        int id = scann.nextInt();

        System.out.print("Introduce el nombre del departamento: ");
        String nombre = scann.next();

        DTODepartamento departamento = new DTODepartamento(id, nombre);

        DAODepartamento departamentoDao = new DAODepartamento();
        departamentoDao.addDepartamento(departamento);
    }

    public static void modDpto() throws SQLException {
        System.out.print("Introduce el id del departamento: ");
        int id = scann.nextInt();

        System.out.print("Introduce el nombre del departamento: ");
        String nombre = scann.next();

        DTODepartamento departamento = new DTODepartamento(id, nombre);

        DAODepartamento departamentoDao = new DAODepartamento();
        departamentoDao.updateDepartamento(departamento);
    }

    public static void menuEliminarDpto() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          DEPARTAMENTOS          #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Eliminar por id             #");
        System.out.println("#  2. Eliminar por nombre         #");
        System.out.println("#  3. Eliminar tabla completa     #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-3): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                eliminaDptoPorId();
                break;
            case 2:
                eliminaDptoPorNombre();
                break;
            case 3:
                DAODepartamento departamentoDao = new DAODepartamento();
                departamentoDao.deleteAllDepartamentos();
                menuDepartamentos();
                break;
            default:
                menuEliminarDpto();
                break;
        }
    }

    public static void eliminaDptoPorId() throws SQLException {
        System.out.println("Indica el id del departamento a eliminar: ");
        int id = scann.nextInt();

        DAODepartamento departamentoDao = new DAODepartamento();
        departamentoDao.deleteDepartamentoById(id);
    }

    public static void eliminaDptoPorNombre() throws SQLException {
        System.out.println("Indica el nombre del departamento a eliminar: ");
        String nombre = scann.nextLine();

        DAODepartamento departamentoDao = new DAODepartamento();
        departamentoDao.deleteDepartamentoByName(nombre);
    }

    public static void menuEmpleados() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          EMPLEADOS              #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Mostrar empleados           #");
        System.out.println("#  2. Seleccionar empleado        #");
        System.out.println("#  3. Añadir empleado             #");
        System.out.println("#  4. Modificar empleado          #");
        System.out.println("#  5. Eliminar empleado           #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-5): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                menuMostrarEmpleados();
                break;
            case 2:
                System.out.println(mostrarEmpleado());
                break;
            case 3:
                añadirEmpleado();
                mostrarEmpleados();
                break;
            case 4:
                modificaEmpleado();
                mostrarEmpleados();
                break;
            case 5:
                menuEliminarEmpleado();
                break;
            default:
                menuEmpleados();
                break;
        }
    }

    public static void menuMostrarEmpleados() throws SQLException {
        System.out.println("######################################");
        System.out.println("#                                    #");
        System.out.println("#          EMPLEADOS                 #");
        System.out.println("#                                    #");
        System.out.println("######################################");
        System.out.println("#  1. Mostrar todos los empleados    #");
        System.out.println("#  2. Mostrar empleados por id_dpto  #");
        System.out.println("######################################");
        System.out.print("# Elige una opción (1-2): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                mostrarEmpleados();
                break;
            case 2:
                mostrarEmpleadoPorIdDpto();
                break;
            default:
                menuMostrarEmpleados();
                break;
        }
    }

    public static void mostrarEmpleados() throws SQLException {
        ArrayList<DTOEmpleado>listaEmpleados;
        DAOEmpleado daoEmpleado = new DAOEmpleado();

        listaEmpleados = daoEmpleado.selectAll();

        for (DTOEmpleado emp : listaEmpleados){
            System.out.println(emp);
        }
    }

    public static void mostrarEmpleadoPorIdDpto() throws SQLException {
        System.out.print("Indica el id del departamento que quieres ver los empleados: ");
        int id = scann.nextInt();

        ArrayList<DTOEmpleado>listaEmpleados;
        DAOEmpleado daoEmpleado = new DAOEmpleado();

        listaEmpleados = daoEmpleado.selectAllByIdDpto(id);

        for (DTOEmpleado emp : listaEmpleados){
            System.out.println(emp);
        }
    }

    public static DTOEmpleado mostrarEmpleado() throws SQLException {
        System.out.print("Indica el id del empleado: ");
        int id = scann.nextInt();

        DAOEmpleado daoEmpleado = new DAOEmpleado();

        return daoEmpleado.selectEmpleadoById(id);
    }

    public static void añadirEmpleado() throws SQLException {
        System.out.print("Indica el id del empleado: ");
        int id = scann.nextInt();

        System.out.print("Indica el nombre del empleado: ");
        String nombre = scann.next();

        System.out.print("Indica la edad del empleado: ");
        int edad = scann.nextInt();

        System.out.print("Indica el id del departamento al que pertenece el empleado: ");
        int dpto_id = scann.nextInt();

        DTOEmpleado empleado = new DTOEmpleado(id, nombre, edad, dpto_id);

        DAOEmpleado daoEmpleado = new DAOEmpleado();
        daoEmpleado.addEmpleado(empleado);
    }

    public static void modificaEmpleado() throws SQLException {
        System.out.print("Indica el id del empleado: ");
        int id = scann.nextInt();

        System.out.print("Indica el nombre del empleado: ");
        String nombre = scann.next();

        System.out.print("Indica la edad del empleado: ");
        int edad = scann.nextInt();

        System.out.print("Indica el id del departamento al que pertenece el empleado: ");
        int dpto_id = scann.nextInt();

        DTOEmpleado empleado = new DTOEmpleado(id, nombre, edad, dpto_id);

        DAOEmpleado daoEmpleado = new DAOEmpleado();
        daoEmpleado.updateEmpleado(empleado);
    }

    public static void menuEliminarEmpleado() throws SQLException {
        System.out.println("###################################");
        System.out.println("#                                 #");
        System.out.println("#          EMPLEADOS              #");
        System.out.println("#                                 #");
        System.out.println("###################################");
        System.out.println("#  1. Eliminar por id             #");
        System.out.println("#  2. Eliminar tabla completa     #");
        System.out.println("###################################");
        System.out.print("# Elige una opción (1-2): ");

        int eleccion = scann.nextInt();

        switch (eleccion){
            case 1:
                eliminaEmpPorId();
                mostrarEmpleados();
                break;
            case 2:
                DAOEmpleado daoEmpleado = new DAOEmpleado();
                daoEmpleado.deleteAllEmpleados();
                menuEmpleados();
                break;
            default:
                menuEliminarEmpleado();
                break;
        }
    }

    public static void eliminaEmpPorId() throws SQLException {
        System.out.print("Indica el id del empleado: ");
        int id = scann.nextInt();

        DAOEmpleado daoEmpleado = new DAOEmpleado();
        daoEmpleado.deleteEmpleadoById(id);
    }
}