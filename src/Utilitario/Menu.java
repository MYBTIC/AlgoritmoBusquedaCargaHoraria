package Utilitario;

import java.util.Scanner;

import CargaHoraria.Horario;

public class Menu {
    private static final int Delay = 8000;

    public static int mmMostrarMenuCargaHorario(String mmUsuarioLogeado, String mmTemaAsignado)
            throws Exception {
        Utilitario.limpiarPantalla();
        int mmOpcion = 0;
        System.out.println("--------------------------------------------------------");
        System.out.println(Color.GREEN + "\t\tCarga horaria de <<" + mmTemaAsignado + ">>");
        System.out.println("--------------------------------------------------------");
        System.out.println("\n\n\t\t......................");
        System.out.print(Color.CYAN + "\n\t\t + Usuario: " + mmUsuarioLogeado.toUpperCase());

        System.out.println("\n\n\t\t1. Viualizar Medicos");
        System.out.println("\n\t\t2. Viualizar Especialidades");
        System.out.println("\n\t\t3. Viualizar horarios");
        System.out.println("\n\t\t4. Viualizar Horario de una especialidad");
        System.out.println("\n\t\t0. salir");
        System.out.print(Color.UNDERLINED_BLUE + "\n\n\t\t  < + > Digite su opcion: " + Color.RESET);
        mmOpcion = Utilitario.mmValidacion.mmValidacionNumero(mmOpcion);
        Thread.sleep(1200);
        return mmOpcion;
    }

    public static void mmElegirOpcion(String mmUsuarioLogeado, String mmTemaAsignado, String horarioRutaDirectorio,
            Horario horario, Scanner sc)
            throws Exception {
        horario.readHorarioMedicos(horarioRutaDirectorio);
        do {
            switch (Menu.mmMostrarMenuCargaHorario(mmUsuarioLogeado, mmTemaAsignado)) {
                case 0:
                    Utilitario.limpiarPantalla();
                    System.out.println(
                            Color.YELLOW + "\n\n\n\t\t\t\t\tGracias por tu visitaa " + mmUsuarioLogeado.toUpperCase()
                                    + " :3  ....!" + Color.RESET);
                    Thread.sleep(Delay);
                    return;
                case 1:
                    Utilitario.limpiarPantalla();
                    System.out.println(Color.RED + "[+] Listado de <<" + mmTemaAsignado + ">>" + Color.RESET);
                    System.out.print(Color.BLUE);
                    horario.showMedicos();
                    System.out.println(Color.RESET);
                    Thread.sleep(Delay);
                    break;
                case 2:
                    Utilitario.limpiarPantalla();
                    System.out.println(Color.BLUE + "[+] Listado de <<" + mmTemaAsignado + ">>" + Color.RESET);
                    System.out.print(Color.CYAN);
                    horario.showEspecialidad();
                    System.out.print(Color.RESET);
                    Thread.sleep(Delay);
                    break;
                case 3:
                    Utilitario.limpiarPantalla();
                    System.out.println(Color.GREEN + "[+] Listado de <<" + mmTemaAsignado + ">>" + Color.RESET);
                    System.out.print(Color.YELLOW);
                    horario.showHorario();
                    System.out.print(Color.RESET);
                    Thread.sleep(Delay);
                    break;
                case 4:
                    Utilitario.limpiarPantalla();
                    System.out.print(Color.PURPLE + "Ingresa Codigo-Paralelo: " + Color.RESET);
                    String inputString = sc.nextLine();
                    System.out.print(Color.CYAN);
                    horario.busquedaHorario(inputString);
                    System.out.print(Color.RESET);
                    Thread.sleep(Delay);
                    break;
                default:
                    break;
            }
        } while (true);

    }
}
