package CargaHoraria;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Utilitario.Color;
import Utilitario.Utilitario;

public class Horario extends HorarioBase implements IGestiarArchivoHorario {
    List<HorarioFormato> infoFila = new ArrayList<>();
    Set<String> materiasRepetidas = new HashSet<>();
    Set<String> stringsRepetidos = new HashSet<>();
    String[][] matrizHorario = new String[15][7];

    int Conteo = 0;

    @Override
    public void readHorarioMedicos(String directorioHorarios) throws Exception {
        File file = new File(directorioHorarios);
        if (file.isDirectory()) {
            String[] nombresMedicos = file.list();
            for (String nombreMedico : nombresMedicos) {
                if (nombreMedico.endsWith(".csv")) {
                    readAsignaturaMedicos(directorioHorarios + "\\" + nombreMedico);
                    medicos.add(nombreMedico.substring(0, nombreMedico.lastIndexOf(".")));
                }
            }

        } else {
            Utilitario.limpiarPantalla();
            System.out.println(Color.RED
                    + "\n\n\n\t\t\tError al encontrar ruta de directorio: "
                    + "[" + directorioHorarios + "]" + " Corriga y vuelva a ejecutar el programa por favor");
            System.out.println("\n\n\n");
            Thread.sleep(2000);
            System.exit(0);
        }

    }

    public void readAsignaturaMedicos(String rutaPersonalMedico) throws IOException {
        List<String> lineasArchivo = Files.readAllLines(Paths.get(rutaPersonalMedico));
        // Nro.;Codigo;Materia;Paral;Aula;Créditos;Lunes;Martes;Miércoles;Jueves;Viernes;Sábado;Carrera;Obs
        for (String lineaArchivo : lineasArchivo) {
            HorarioFormato hf = new HorarioFormato();
            String[] datos = lineaArchivo.split(";");
            hf.codigo = datos[1];
            hf.especialidad = datos[2];
            hf.paralelo = datos[3];
            hf.aula = datos[4];
            hf.creditos = datos[5];
            hf.horaLunes = horaInicioFin(datos[6]);
            hf.horaMartes = horaInicioFin(datos[7]);
            hf.horaMiercoles = horaInicioFin(datos[8]);
            hf.horaJueves = horaInicioFin(datos[9]);
            hf.horaViernes = horaInicioFin(datos[10]);
            hf.horaSabado = horaInicioFin(datos[11]);

            if (hf.especialidad.equals("Materia")) {
                especialidad.add("");
            } else {
                especialidad.add(hf.especialidad);
            }
            infoFila.add(hf);
            Conteo++;
        }
    }

    @Override
    public void showEspecialidad() {
        for (String especialidadMedico : especialidad) {
            System.out.println(especialidadMedico);
        }
    }

    @Override
    public void showMedicos() {
        for (String medico : medicos) {
            System.out.println(medico);
        }

    }

    public void createHorario() {
        int conteoMaterias = 0, conteoHoras = 0, inicioHorarioL = 7, inicioHorarioM = 7, inicioHorarioMi = 7,
                inicioHorarioJ = 7, inicioHorarioV = 7, inicioHorarioS = 7;
        matrizHorario[0][0] = "Horario";
        matrizHorario[0][1] = "(l) Lunes";
        matrizHorario[0][2] = "(m) Martes";
        matrizHorario[0][3] = "(x) Miercoles";
        matrizHorario[0][4] = "(j) Jueves";
        matrizHorario[0][5] = "(v) Viernes";
        matrizHorario[0][6] = "(s) Sabado";
        matrizHorario[1][0] = "7-8";
        matrizHorario[2][0] = "8-9";
        matrizHorario[3][0] = "9-10";
        matrizHorario[4][0] = "10-11";
        matrizHorario[5][0] = "11-12";
        matrizHorario[6][0] = "12-13";
        matrizHorario[7][0] = "13-14";
        matrizHorario[8][0] = "14-15";
        matrizHorario[9][0] = "15-16";
        matrizHorario[10][0] = "16-17";
        matrizHorario[11][0] = "17-18";
        matrizHorario[12][0] = "18-19";
        matrizHorario[13][0] = "19-20";
        matrizHorario[14][0] = "20-21";

        for (int i = 1; i < matrizHorario.length; i++) {
            for (int j = 1; j < matrizHorario[0].length; j++) {
                if (j == 1) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaLunes, inicioHorarioL)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioL++;
                }
                if (j == 2) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaMartes, inicioHorarioM)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioM++;
                }
                if (j == 3) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaMiercoles, inicioHorarioMi)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioMi++;
                }
                if (j == 4) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaJueves, inicioHorarioJ)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioJ++;
                }
                if (j == 5) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaViernes, inicioHorarioV)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioV++;
                }
                if (j == 6) {
                    for (int j2 = 0; j2 < infoFila.size(); j2++) {
                        if (verificarExistenciaElemento(infoFila.get(j2).horaSabado, inicioHorarioS)) {
                            if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                    && infoFila.get(j2).especialidad.isEmpty()) {
                                conteoMaterias = 0;

                            } else {
                                conteoMaterias++;
                                if (conteoMaterias != 0) {
                                    conteoHoras = conteoMaterias;
                                }

                            }

                        } else if (conteoMaterias != conteoHoras) {
                            conteoMaterias = 0;
                        }

                    }
                    materiasRepetidas = new HashSet<>();
                    stringsRepetidos = new HashSet<>();
                    if (conteoMaterias == 0) {
                        matrizHorario[i][j] = "\t-";
                    } else {
                        matrizHorario[i][j] = "\t" + String.valueOf(conteoMaterias);
                    }
                    conteoMaterias = 0;
                    inicioHorarioS++;
                }
            }
        }
    }

    public void showHorario() {
        createHorario();
        for (String[] horarioFila : matrizHorario) {
            for (String horarioColumna : horarioFila) {
                System.out.print(horarioColumna + "\t");
            }
            System.out.println();
        }

    }

    public boolean verificarCadenaSinRepetidos(String valores) {

        if (materiasRepetidas.contains(valores)) {
            return false; // Se encontró un valores repetido
        } else {

            materiasRepetidas.add(valores);
            return true;
        }

        // No se encontraron valores repetidos
    }

    ArrayList<Integer> horaInicioFin(String horas) {
        int horaInicio = 0, horaFin = 0;
        ArrayList<Integer> horasClases = new ArrayList<>();
        try {
            String[] datos = horas.split("-");
            horaInicio = Integer.parseInt(datos[0]);
            horaFin = Integer.parseInt(datos[1]);
        } catch (Exception e) {
        }
        for (; horaInicio < horaFin;)
            horasClases.add(horaInicio++);
        return horasClases;
    }

    public static boolean verificarExistenciaElemento(ArrayList<Integer> lista, int elemento) {
        // Verificar si existe al menos un elemento en el ArrayList
        return lista.contains(elemento);
    }

    public void metodoBusqueda(String codigoParalelo) {
        int conteoMaterias = 0, conteoHoras = 0, inicioHorarioL = 7, inicioHorarioM = 7, inicioHorarioMi = 7,
                inicioHorarioJ = 7, inicioHorarioV = 7, inicioHorarioS = 7;
        try {
            String[] infoString = codigoParalelo.split("-");
            String controlador = "";
            matrizHorario[0][0] = "Horario";
            matrizHorario[0][1] = "(l) Lunes";
            matrizHorario[0][2] = "(m) Martes";
            matrizHorario[0][3] = "(x) Miercoles";
            matrizHorario[0][4] = "(j) Jueves";
            matrizHorario[0][5] = "(v) Viernes";
            matrizHorario[0][6] = "(s) Sabado";
            matrizHorario[1][0] = "7-8";
            matrizHorario[2][0] = "8-9";
            matrizHorario[3][0] = "9-10";
            matrizHorario[4][0] = "10-11";
            matrizHorario[5][0] = "11-12";
            matrizHorario[6][0] = "12-13";
            matrizHorario[7][0] = "13-14";
            matrizHorario[8][0] = "14-15";
            matrizHorario[9][0] = "15-16";
            matrizHorario[10][0] = "16-17";
            matrizHorario[11][0] = "17-18";
            matrizHorario[12][0] = "18-19";
            matrizHorario[13][0] = "19-20";
            matrizHorario[14][0] = "20-21";
            for (int i = 1; i < matrizHorario.length; i++) {
                for (int j = 1; j < matrizHorario[0].length; j++) {
                    if (j == 1) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaLunes, inicioHorarioL)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {

                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;
                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioL++;
                    }
                    if (j == 2) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaMartes, inicioHorarioM)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {
                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;

                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioM++;
                    }
                    if (j == 3) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaMiercoles, inicioHorarioMi)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {
                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;

                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioMi++;
                    }
                    if (j == 4) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaJueves, inicioHorarioJ)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {
                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;
                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioJ++;
                    }
                    if (j == 5) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaViernes, inicioHorarioV)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {
                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;
                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioV++;
                    }
                    if (j == 6) {
                        for (int j2 = 0; j2 < infoFila.size(); j2++) {
                            if (verificarExistenciaElemento(infoFila.get(j2).horaSabado, inicioHorarioS)) {
                                if (verificarCadenaSinRepetidos(infoFila.get(j2).especialidad)
                                        && infoFila.get(j2).especialidad.isEmpty()) {
                                    conteoMaterias = 0;

                                } else {
                                    conteoMaterias++;
                                    if (conteoMaterias != 0) {
                                        conteoHoras = conteoMaterias;
                                        if (infoFila.get(j2).codigo.equals(infoString[0])
                                                && infoFila.get(j2).paralelo.equals(infoString[1])) {
                                            controlador = String.valueOf(conteoMaterias) + "*";
                                        } else {
                                            controlador = String.valueOf(conteoMaterias);
                                        }
                                    }

                                }

                            } else if (conteoMaterias != conteoHoras) {
                                conteoMaterias = 0;
                            }

                        }
                        materiasRepetidas = new HashSet<>();
                        stringsRepetidos = new HashSet<>();
                        if (conteoMaterias == 0) {
                            matrizHorario[i][j] = "\t-";
                        } else {
                            matrizHorario[i][j] = "\t" + controlador;
                        }
                        conteoMaterias = 0;
                        inicioHorarioS++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\\Error!!!!..........Ingrese en el formato que se le pide ");
        }
    }

    public void busquedaHorario(String codigoParalelo) {
        metodoBusqueda(codigoParalelo);
        for (String[] horarioFila : matrizHorario) {
            for (String horarioColumna : horarioFila) {
                System.out.print(horarioColumna + "\t");
            }
            System.out.println();
        }
    }
}
