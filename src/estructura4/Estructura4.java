package estructura4;
import java.util.*;

public class Estructura4 {

    static class Nodo{
        String nombre;
        Equipo equipo1;
        Equipo equipo2;
        Equipo ganador;
        Nodo izquierdo;
        Nodo derecho;
    }
    static class Equipo{
        String codigo;
        String nombre;
    }

    static ArrayList<Equipo> equipos = new ArrayList<>();
    static Nodo semifinal1 = new Nodo();
    static Nodo semifinal2 = new Nodo();
    static Nodo finalT = new Nodo();

    public static void main(String[] args) {
        semifinal1.nombre = "Semifinal 1";
        semifinal2.nombre = "Semifinal 2";
        finalT.nombre = "Final";
        finalT.izquierdo = semifinal1;
        finalT.derecho = semifinal2;

        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n=== Campus Clash ===");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Asignar equipo a semifinal");
            System.out.println("3. Ver semifinales");
            System.out.println("4. Registrar ganador de semifinal");
            System.out.println("5. Registrar ganador de la final");
            System.out.println("6. Ver cuadro del torneo");
            System.out.println("0. Salir");
            System.out.print("\nOpcion: ");

            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida, ingrese un numero");
                sc.nextLine();
                op = -1;
            }

            switch (op) {
                case 1: registrarEquipo(sc); break;
                case 2: asignarASemifinal(sc); break;
                case 3: verSemifinales(); break;
                case 4: registrarGanadorSemifinal(sc); break;
                case 5: registrarGanadorFinal(sc); break;
                case 6: verCuadro(); break;
                case 0: System.out.println("Fin del torneo"); break;
                default:
                    if (op != -1) System.out.println("Opcion invalida");
                    break;
            }
        } while (op != 0);
    }

    static void registrarEquipo(Scanner sc) {
        System.out.print("Codigo unico: ");
        String cod = sc.nextLine().trim();

        for (Equipo e : equipos) {
            if (e.codigo.equalsIgnoreCase(cod)) {
                System.out.println("Ya existe un equipo con ese codigo");
                return;
            }
        }

        Equipo e = new Equipo();
        e.codigo = cod;
        System.out.print("Nombre del equipo: ");
        e.nombre = sc.nextLine();
        equipos.add(e);
        System.out.println("Equipo '" + e.nombre + "' registrado.");
    }

    static void asignarASemifinal(Scanner sc) {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados todavia");
            return;
        }
        
        System.out.print("Ingrese el codigo del equipo: ");
        String cod = sc.nextLine().trim();
        Equipo encontrado = null;
        for (Equipo e : equipos) {
            if (e.codigo.equalsIgnoreCase(cod)) {
                encontrado = e;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("Codigo no encontrado");
            return;
        }
        if (encontrado == semifinal1.equipo1 || encontrado == semifinal1.equipo2 || encontrado == semifinal2.equipo1 || encontrado == semifinal2.equipo2) {
            System.out.println("Este equipo ya esta asignado a una semifinal");
            return;
        }

        System.out.print("A que semifinal lo asignas? (1 o 2): ");
        int i;
        try {
            i = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ingrese 1 o 2");
            sc.nextLine();
            return;
        }

        if (i != 1 && i != 2) {
            System.out.println("Opcion invalida, ingrese 1 o 2");
            return;
        }

        Nodo semi;
        if (i == 1) {
            semi = semifinal1;
        } else {
            semi = semifinal2;
        }

        if (semi.equipo1 == null) {
            semi.equipo1 = encontrado;
            System.out.println(encontrado.nombre + " asignado a " + semi.nombre + " (esperando rival)");
        } else if (semi.equipo2 == null) {
            semi.equipo2 = encontrado;
            System.out.println(encontrado.nombre + " asignado a " + semi.nombre);
            System.out.println(semi.nombre + " lista: " + semi.equipo1.nombre + " vs " + semi.equipo2.nombre);
        } else {
            System.out.println(semi.nombre + " ya tiene 2 equipos asignados");
        }

        if (semifinal1.equipo1 != null && semifinal1.equipo2 != null && semifinal2.equipo1 != null && semifinal2.equipo2 != null) {
            System.out.println("\nTodas las semifinales ya estan formadas");
        }
    }

    static void verSemifinales() {
        if (semifinal1.equipo1 == null && semifinal2.equipo1 == null) {
            System.out.println("No hay equipos asignados todavia");
            return;
        }
        System.out.println("\n=== Semifinales ===");

        String e1sf1;
        if (semifinal1.equipo1 != null) {
            e1sf1 = semifinal1.equipo1.nombre;
        } else {
            e1sf1 = "?";
        }
        String e2sf1;
        if (semifinal1.equipo2 != null) {
            e2sf1 = semifinal1.equipo2.nombre;
        } else {
            e2sf1 = "?";
        }
        System.out.println("Semifinal 1: " + e1sf1 + " vs " + e2sf1);
        if (semifinal1.ganador != null) {
            System.out.println("  Ganador: " + semifinal1.ganador.nombre);
        } else {
            System.out.println("  Ganador: Pendiente");
        }

        String e1sf2;
        if (semifinal2.equipo1 != null) {
            e1sf2 = semifinal2.equipo1.nombre;
        } else {
            e1sf2 = "?";
        }
        String e2sf2;
        if (semifinal2.equipo2 != null) {
            e2sf2 = semifinal2.equipo2.nombre;
        } else {
            e2sf2 = "?";
        }
        System.out.println("Semifinal 2: " + e1sf2 + " vs " + e2sf2);
        if (semifinal2.ganador != null) {
            System.out.println("  Ganador: " + semifinal2.ganador.nombre);
        } else {
            System.out.println("  Ganador: Pendiente");
        }
    }

    static void registrarGanadorSemifinal(Scanner sc) {
        if (semifinal1.equipo1 == null || semifinal1.equipo2 == null || semifinal2.equipo1 == null || semifinal2.equipo2 == null) {
            System.out.println("Las semifinales aun no estan completas");
            return;
        }

        System.out.print("Cual semifinal? (1 o 2): ");
        int i;
        try {
            i = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ingrese 1 o 2");
            sc.nextLine();
            return;
        }

        if (i != 1 && i != 2) {
            System.out.println("Opcion invalida, ingrese 1 o 2");
            return;
        }

        Nodo semi;
        if (i == 1) {
            semi = semifinal1;
        } else {
            semi = semifinal2;
        }

        System.out.println("Ganador de " + semi.nombre + ":");
        System.out.println("1. " + semi.equipo1.nombre);
        System.out.println("2. " + semi.equipo2.nombre);
        System.out.print("Opcion: ");
        int op;
        try {
            op = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Opcion invalida");
            sc.nextLine();
            return;
        }

        if (op == 1) {
            semi.ganador = semi.equipo1;
        } else if (op == 2) {
            semi.ganador = semi.equipo2;
        } else {
            System.out.println("Opcion invalida");
            return;
        }

        System.out.println(semi.ganador.nombre + " registrado como ganador de " + semi.nombre);

        if (semifinal1.ganador != null && semifinal2.ganador != null) {
            finalT.equipo1 = semifinal1.ganador;
            finalT.equipo2 = semifinal2.ganador;
            System.out.println("\nFinal formada: " + finalT.equipo1.nombre + " vs " + finalT.equipo2.nombre);
        }
    }

    static void registrarGanadorFinal(Scanner sc) {
        if (finalT.equipo1 == null) {
            System.out.println("La final aun no esta formada");
            return;
        }
        System.out.println("Ganador de la Final:");
        System.out.println("1. " + finalT.equipo1.nombre);
        System.out.println("2. " + finalT.equipo2.nombre);
        System.out.print("Opcion: ");
        int op;
        try {
            op = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Opcion invalida");
            sc.nextLine();
            return;
        }

        if (op == 1) {
            finalT.ganador = finalT.equipo1;
        } else if (op == 2) {
            finalT.ganador = finalT.equipo2;
        } else {
            System.out.println("Opcion invalida");
            return;
        }
        System.out.println(finalT.ganador.nombre + " es el campeon!");
    }

    static void verCuadro() {
        Nodo s1 = finalT.izquierdo;
        Nodo s2 = finalT.derecho;
        System.out.println("\n======BRACKETS DEL TORNEO======\n");
        System.out.println("            [" + finalT.nombre + "]");
        System.out.println("           /        \\");
        System.out.println("   [" + s1.nombre + "]  [" + s2.nombre + "]");
        System.out.println("================================");

        if (s1.equipo1 != null) {
            String rival1;
            if (s1.equipo2 != null) {
                rival1 = s1.equipo2.nombre;
            } else {
                rival1 = "?";
            }
            System.out.println("Semifinal 1: " + s1.equipo1.nombre + " vs " + rival1);
            String txtG1;
            if (s1.ganador != null) {
                txtG1 = s1.ganador.nombre;
            } else {
                txtG1 = "Pendiente";
            }
            System.out.println("Ganador: " + txtG1);
        } else {
            System.out.println("Semifinal 1: (sin equipos aun)");
        }

        if (s2.equipo1 != null) {
            String rival2;
            if (s2.equipo2 != null) {
                rival2 = s2.equipo2.nombre;
            } else {
                rival2 = "?";
            }
            System.out.println("Semifinal 2: " + s2.equipo1.nombre + " vs " + rival2);
            String txtG2;
            if (s2.ganador != null) {
                txtG2 = s2.ganador.nombre;
            } else {
                txtG2 = "Pendiente";
            }
            System.out.println("Ganador : " + txtG2);
        } else {
            System.out.println("Semifinal 2: (sin equipos aun)");
        }

        System.out.println();

        if (finalT.equipo1 != null) {
            System.out.println("Final: " + finalT.equipo1.nombre + " vs " + finalT.equipo2.nombre);
            String txtCampeon;
            if (finalT.ganador != null) {
                txtCampeon =finalT.ganador.nombre;
            } else {
                txtCampeon = "Pendiente";
            }
            System.out.println("Campeon: " + txtCampeon);
        } else {
            System.out.println("Final: (pendiente de semifinales)");
        }
        System.out.println("================================");
    }
}