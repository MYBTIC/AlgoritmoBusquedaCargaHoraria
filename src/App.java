
import java.util.Scanner;
import CargaHoraria.Horario;
import Utilitario.Color;
import Utilitario.Menu;
import Utilitario.Utilitario;

public class App {
    private static String horarioRutaDirectorio = "Horarios";
    private static Scanner sc = new Scanner(System.in);
    static Horario horario = new Horario();
    static String entradaOpcion4;
    private static final String mmCedula = "1750352450";
    private static final String mmNombreCompleto = "Maximiliano Madrid Benavides";
    public static final String mmCorreoElectronico = "maximiliano.madrid@epn.edu.ec";
    public static final String mmProfe = "profe";
    private static final String mmClaveProfe = "1234";
    private static final String mmTemaAsignado = "Medicos";

    public static void main(String[] args) throws Exception {

        System.out.println(Color.GREEN + "Cedula: " + mmCedula);
        System.out.println("CORREO: " + Utilitario.mmConvertirDatosPersonales.mmConvertirCorreo(mmCorreoElectronico));
        System.out.println(
                "NOMBRE: " + Utilitario.mmConvertirDatosPersonales.mmConvertirNombre(mmNombreCompleto) + Color.RESET);
        Thread.sleep(2000);
        if (Utilitario.mmLoginMaximiliano(mmCorreoElectronico, mmCedula, mmProfe, mmClaveProfe) == true
                && Utilitario.mmVariableValidadorUsuario == 0) {
            Menu.mmElegirOpcion(mmCorreoElectronico, mmTemaAsignado, horarioRutaDirectorio, horario, sc);
        } else if (Utilitario.mmVariableValidadorUsuario == 1) {
            Menu.mmElegirOpcion(mmProfe, mmTemaAsignado, horarioRutaDirectorio, horario, sc);
        }

    }

}
