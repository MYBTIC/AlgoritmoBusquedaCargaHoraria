package Utilitario;

import java.io.Console;
import java.util.Scanner;

public class Utilitario {

    public static byte mmVariableValidadorUsuario = 0;
    private static Scanner Input = new Scanner(System.in);

    /**
     * @Recomendacion Modifique el rango de aceptacion
     * @param num Recepta el numero a validar
     * @return El numero Ya validado
     */
    public class mmValidacion {
        public static int mmValidacionNumero(int num) {

            boolean control = true;
            do {
                try {
                    num = Integer.parseInt(Input.nextLine());
                    while (num < 0 || num > 99) {
                        Utilitario.limpiarPantalla();
                        System.out.print("\rDigite un numero positivo y en el rango que se le solicita: ");
                        num = Integer.parseInt(Input.nextLine());

                    }
                    control = true;
                } catch (NumberFormatException e) {
                    Utilitario.limpiarPantalla();
                    System.out.print("\rDigite un numero, No caracter: ");
                    control = false;
                }
            } while (control == false);
            return num;
        }

        public static boolean mmValidadorCaracter(String sexo) {
            String mmSexo1 = "F", mmSexo2 = "M";
            if (sexo.equals(mmSexo1) || sexo.equals(mmSexo1.toLowerCase()) || sexo.equals(mmSexo2)
                    || sexo.equals(mmSexo2.toLowerCase())) {
                return true;
            } else {
                return false;
            }

        }

        public static char mmVerificadorCaracter(String validadorCaracter) {
            char Caracter = 'o';

            do {
                System.out.print("\nDigite un caracter, M o F: ");
                validadorCaracter = Input.nextLine();

            } while (Utilitario.mmValidacion.mmValidadorCaracter(validadorCaracter) == false);
            validadorCaracter = validadorCaracter.toUpperCase();
            if ((Utilitario.mmValidacion.mmValidadorCaracter(validadorCaracter) == true)) {
                Caracter = validadorCaracter.charAt(0);
            }

            return Caracter;
        }

        public class mmConvertirDatosPersonales {
            public static String mmConvertirCorreo(String mmCorreoElectronico) {
                String mmCorreoValidado = mmCorreoElectronico.toLowerCase();

                return mmCorreoValidado;
            }

            public static String mmConvertirNombre(String mmNombre) {
                String mmNombreValidado = mmNombre.toUpperCase();
                return mmNombreValidado;
            }

        }

        public static String mmValidadorNick(String mmCadenaaValidar) {

            String[] mmStringValido = new String[mmCadenaaValidar.length()];
            StringBuilder mmStringBuilder = new StringBuilder();
            for (int i = 0; i < mmCadenaaValidar.length(); i++) {
                mmStringValido[i] = String.valueOf(mmCadenaaValidar.charAt(i));
            }
            for (int i = 0; i < mmStringValido.length; i++) {
                if (mmStringValido[i].equals(" ")) {
                    mmStringValido[i] = "";
                }
                mmStringValido[0] = mmStringValido[0].toUpperCase();
            }
            for (String string : mmStringValido) {
                mmStringBuilder.append(string);
            }
            mmCadenaaValidar = mmStringBuilder.toString();

            return mmCadenaaValidar;
        }

    }

    public static boolean mmLoginMaximiliano(String mmCorreoElectronico, String mmCedula, String mmProfe,
            String mmClaveProfe) throws InterruptedException {
        Utilitario.limpiarPantalla();
        String mmValidacionUsuario;
        char[] contrasena;
        int mmContador = 0;

        do {
            mmContador++;
            Utilitario.limpiarPantalla();
            Console consola = System.console();
            System.out.println(Color.YELLOW + "--------------------------------------------------------");
            System.out.println("\t\t\tLOGIN");
            System.out.println("--------------------------------------------------------");
            System.out.println("\n\n\t\t......................" + Color.RESET);
            System.out.print(Color.BLUE + "\n\t\t + Usuario: ");
            mmValidacionUsuario = Input.nextLine();
            contrasena = consola.readPassword("\n\t\t + Clave: ");
            String contrasenaEnmascarada = Utilitario.mmEnmascararTexto(new String(contrasena));
            System.out.print("\n\t\t + Clave: " + contrasenaEnmascarada);
            System.out.println(Color.RESET + Color.YELLOW + "\n\n\t\t......................" + Color.RESET);
            System.out.println(Color.PURPLE + "\t\t* Nro de intentos: " + mmContador + Color.RESET);
            Thread.sleep(1200);

            if (mmValidacionUsuario.equals(mmCorreoElectronico)) {
                mmVariableValidadorUsuario = 0;
                if ((new String(contrasena)).equals(mmCedula)) {
                    mmMostrarBienvenida(mmVariableValidadorUsuario, mmCorreoElectronico, mmProfe);
                    return true;
                }
            } else if (mmValidacionUsuario.equals(mmProfe)) {
                mmVariableValidadorUsuario = 1;
                if ((new String(contrasena)).equals(mmClaveProfe)) {
                    mmMostrarBienvenida(mmVariableValidadorUsuario, mmCorreoElectronico, mmProfe);
                    return true;
                }
            }
        } while (mmContador < 3);
        Utilitario.limpiarPantalla();
        System.out.println(Color.RED + "\n\n\n\t\t\tLo sentimos usuario y clave son incorrectos...!!\n\n\n"
                + Color.RESET);
        return false;

    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String mmEnmascararTexto(String texto) {
        int longitud = texto.length();
        StringBuilder enmascarado = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            enmascarado.append('*');
        }

        return enmascarado.toString();
    }

    public class mmConvertirDatosPersonales {
        public static String mmConvertirCorreo(String mmCorreoElectronico) {
            String mmCorreoValidado = mmCorreoElectronico.toLowerCase();

            return mmCorreoValidado;
        }

        public static String mmConvertirNombre(String mmNombre) {
            String mmNombreValidado = mmNombre.toUpperCase();
            return mmNombreValidado;
        }

    }

    public static void mmMostrarBienvenida(Byte mmValidacion, String mmCorreoElectronico, String mmProfe)
            throws InterruptedException {
        Utilitario.limpiarPantalla();
        if (mmValidacion == 0) {
            System.out.println("\n\n\t\t::Bienvenido " + mmCorreoElectronico.toUpperCase());
            Thread.sleep(1200);
        } else {
            System.out.println("\n\n\t\t::Bienvenido " + mmProfe.toUpperCase());
            Thread.sleep(1200);
        }
    }

    public static String replaceCharacter(String cadena, char caracterAntiguo, char caracterNuevo) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            if (caracter == caracterAntiguo) {
                builder.append(caracterNuevo);
            } else {
                builder.append(caracter);
            }
        }

        return builder.toString();
    }

}
