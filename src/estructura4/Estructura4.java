package estructura4;
import java.util.*;

public class Estructura4 {

    static class Nodo {
        String nombre;
        String ganador;
        Equipo equipo;
        Nodo izquierdo;
        Nodo derecho;
    }

    static class Equipo {
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
        
        if ((semifinal1.izquierdo != null && semifinal1.izquierdo.equipo.codigo.equalsIgnoreCase(encontrado.codigo)) || (semifinal1.derecho != null && semifinal1.derecho.equipo.codigo.equalsIgnoreCase(encontrado.codigo)) || (semifinal2.izquierdo != null && semifinal2.izquierdo.equipo.codigo.equalsIgnoreCase(encontrado.codigo)) || (semifinal2.derecho != null && semifinal2.derecho.equipo.codigo.equalsIgnoreCase(encontrado.codigo))) {
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

        if (semi.izquierdo == null) {
            semi.izquierdo = new Nodo();
            semi.izquierdo.nombre = encontrado.nombre;
            semi.izquierdo.equipo = encontrado;
            System.out.println(encontrado.nombre + " asignado a " + semi.nombre + " (esperando rival)");
        } else if (semi.derecho == null) {
            semi.derecho = new Nodo();
            semi.derecho.nombre = encontrado.nombre;
            semi.derecho.equipo = encontrado;
            System.out.println(encontrado.nombre + " asignado a " + semi.nombre);
            System.out.println(semi.nombre + " lista: " + semi.izquierdo.nombre + " vs " + semi.derecho.nombre);
        } else {
            System.out.println(semi.nombre + " ya tiene 2 equipos asignados");
        }

        if (semifinal1.izquierdo != null && semifinal1.derecho != null && semifinal2.izquierdo != null && semifinal2.derecho != null) {
            System.out.println("\nTodas las semifinales ya estan formadas");
        }
    }

    static void verSemifinales() {
        if (semifinal1.izquierdo == null && semifinal2.izquierdo == null) {
            System.out.println("No hay equipos asignados todavia");
            return;
        }
        System.out.println("\n=== Semifinales ===");

        String e1sf1;
        if (semifinal1.izquierdo != null) {
            e1sf1 = semifinal1.izquierdo.nombre;
        } else {
            e1sf1 = "?";
        }

        String e2sf1;
        if (semifinal1.derecho != null) {
            e2sf1 = semifinal1.derecho.nombre;
        } else {
            e2sf1 = "?";
        }

        System.out.println("Semifinal 1: " + e1sf1 + " vs " + e2sf1);
        if (semifinal1.ganador != null) {
            System.out.println("Ganador: " + semifinal1.ganador);
        } else {
            System.out.println("Ganador: Pendiente");
        }

        String e1sf2;
        if (semifinal2.izquierdo != null) {
            e1sf2 = semifinal2.izquierdo.nombre;
        } else {
            e1sf2 = "?";
        }

        String e2sf2;
        if (semifinal2.derecho != null) {
            e2sf2 = semifinal2.derecho.nombre;
        } else {
            e2sf2 = "?";
        }

        System.out.println("Semifinal 2: " + e1sf2 + " vs " + e2sf2);
        if (semifinal2.ganador != null) {
            System.out.println("Ganador: " + semifinal2.ganador);
        } else {
            System.out.println("Ganador: Pendiente");
        }
    }

    static void registrarGanadorSemifinal(Scanner sc) {
        if (semifinal1.izquierdo == null || semifinal1.derecho == null || semifinal2.izquierdo == null || semifinal2.derecho == null) {
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
        System.out.println("1. " + semi.izquierdo.nombre);
        System.out.println("2. " + semi.derecho.nombre);
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
            semi.ganador = semi.izquierdo.nombre;
        } else if (op == 2) {
            semi.ganador = semi.derecho.nombre;
        } else {
            System.out.println("Opcion invalida");
            return;
        }

        System.out.println(semi.ganador + " registrado como ganador de " + semi.nombre);

        if (semifinal1.ganador != null && semifinal2.ganador != null) {
            System.out.println("\nFinal formada: " + semifinal1.ganador + " vs " + semifinal2.ganador);
        }
    }

    static void registrarGanadorFinal(Scanner sc) {
        if (semifinal1.ganador == null || semifinal2.ganador == null) {
            System.out.println("La final aun no esta formada");
            return;
        }
        System.out.println("Ganador de la Final:");
        System.out.println("1. " + semifinal1.ganador);
        System.out.println("2. " + semifinal2.ganador);
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
            finalT.ganador = semifinal1.ganador;
        } else if (op == 2) {
            finalT.ganador = semifinal2.ganador;
        } else {
            System.out.println("Opcion invalida");
            return;
        }
        System.out.println(finalT.ganador + " es el campeon!");
    }

    static void verCuadro() {
        Nodo s1 = finalT.izquierdo;
        Nodo s2 = finalT.derecho;
        System.out.println("\n======BRACKETS DEL TORNEO======\n");
        System.out.println("            [" + finalT.nombre + "]");
        System.out.println("           /        \\");
        System.out.println("   [" + s1.nombre + "]  [" + s2.nombre + "]");
        System.out.println("================================");

        if (s1.izquierdo != null) {
            String rival1;
            if (s1.derecho != null) {
                rival1 = s1.derecho.nombre;
            } else {
                rival1 = "?";
            }
            System.out.println("Semifinal 1: " + s1.izquierdo.nombre + " vs " + rival1);
            
            String txtG1;
            if (s1.ganador != null) {
                txtG1 = s1.ganador;
            } else {
                txtG1 = "Pendiente";
            }
            System.out.println("Ganador: " + txtG1);
        } else {
            System.out.println("Semifinal 1: (sin equipos aun)");
        }

        if (s2.izquierdo != null) {
            String rival2;
            if (s2.derecho != null) {
                rival2 = s2.derecho.nombre;
            } else {
                rival2 = "?";
            }
            System.out.println("Semifinal 2: " + s2.izquierdo.nombre + " vs " + rival2);
            
            String txtG2;
            if (s2.ganador != null) {
                txtG2 = s2.ganador;
            } else {
                txtG2 = "Pendiente";
            }
            System.out.println("Ganador: " + txtG2);
        } else {
            System.out.println("Semifinal 2: (sin equipos aun)");
        }

        System.out.println();

        if (s1.ganador != null && s2.ganador != null) {
            System.out.println("Final: " + s1.ganador + " vs " + s2.ganador);
            
            String txtCampeon;
            if (finalT.ganador != null) {
                txtCampeon = finalT.ganador;
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