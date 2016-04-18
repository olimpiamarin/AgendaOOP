import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Manu on 18-Apr-16.
 */
public class AgendaOOP {
    /* utility methods for INPUT/OUTPUT using a GUI or Console, please leave them as they are */

    // GUI
    public static int readIntGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        int returnValue = Integer.parseInt(input);
        return returnValue;
    }

    public static double readDoubleGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        double returnValue = Double.parseDouble(input);
        return returnValue;
    }

    public static String readStringGUI(String label) {
        String input = JOptionPane.showInputDialog(null,
                label);
        return input;
    }

    public static void printGUI(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(int text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public static void printGUI(double text) {
        JOptionPane.showMessageDialog(null, text);
    }

    // CONSOLE
    public static String readStringConsole(String label) {
        System.out.print(label);
        String input = new Scanner(System.in).nextLine();
        return input;
    }

    public static int readIntConsole(String label) {
        System.out.print(label);
        int input = new Scanner(System.in).nextInt();
        return input;
    }

    public static double readDoubleConsole(String label) {
        System.out.print(label);
        double input = new Scanner(System.in).nextDouble();
        return input;
    }

    public static void printConsole(String text) {
        System.out.println(text);
    }

    public static void printConsole(int text) {
        System.out.println(text);
    }

    public static void printConsole(double text) {
        System.out.println(text);
    }

    /* end of utility methods*/

//******************************************************************************************************************
    //**************************************************************************************************************
        //**********************************************************************************************************

    //Start of actual code

    static Contact[] agenda = new Contact[10]; //declarare+initializare
    static int index = 0;

    //Functia principala, main
    public static void main(String[] arguments) {

        //Construire meniu

        int opt = 0;
        String opt1;
        do {
            printConsole("Meniul principal:"); //l-am facut pe orizontala pentru ca imi ocupa prea mult din consola
            printConsole("1-Afisarea sirului de nume / 2-Creare nume / 3-Cautare nume / 4-Modificare nume / 5-Stergere nume");
            printConsole("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            opt = readIntConsole("Alegeti optiunea dorita:");
            System.out.println();

            if (opt == 1)
                listare();
            else if (opt == 2) {
                boolean flag=false;
                printConsole("a. creare simpla // b. creare fara duplicat");
                opt1 = readStringConsole("Alegeti optiunea dorita (a/b): ");

                if (opt1.equals("a")) {
                    String n = readStringConsole("Introduceti numele: ");
                    String t = readStringConsole("Introduceti numarul de telefon: ");
                    creare(n, t);
                    flag=true;
                    System.out.println();
                }
                if (opt1.equals("b")) {
                    String n = readStringConsole("Introduceti numele: ");
                    String t = readStringConsole("Introduceti numarul de telefon: ");
                    creareFaraDuplicat(n, t);
                    flag=true;

                }
                if (flag==false) {
                    printConsole("Optiune nevalida!");
                    System.out.println();
                }
            }

            else if (opt == 3){
                String nume = readStringConsole("Introduceti numele cautat: ");
                cautareAfisare(nume);
            }
            else if (opt ==4){
                String nume = readStringConsole("Introduceti numele de modificat:");
                modificare(nume);
            }
            else if (opt == 5){
                String nume = readStringConsole("introduceti numele de sters:");
                stergere(nume);
            }

        } while (opt != 6);
    }


    //METODE

    //AFISARE SIR DE NUME
    public static void listare() {
        boolean flag=false;
        for (int i = 0; i < agenda.length; i++) {
            if (agenda[i] != null) {  // => la inceput nu afiseaza nimic, pentru ca nu am introdus nume
                Contact p = agenda[i];
                printConsole(p.getNume());
                printConsole(p.getTelefon());
                flag = true;
            }
        }
        if(flag==false) {
                System.out.println("Inca nu exista contacte!");
            }
        System.out.println();
    }

    //CREARE NUME NOU IN AGENDA
    public static void creare(String nume, String telefon) {
        Contact p = new Contact(nume, telefon);
        agenda[index] = p;
        index++;
    }

    //CREARE NUME NOU FARA DUPLICAT
    public static void creareFaraDuplicat(String nume, String telefon) {
        int i = cautare(nume); //cautam daca mai exista persoana cu acelasi nume
        if (i == -1) {  //true inseamna ca inca nu exista numele introdus
            if (index < agenda.length) {
                Contact p = new Contact(nume, telefon);
                agenda[index] = p;
                index++;
            } else { // incercam sa gasim locuri libere in sir
                for (int j = 0; j < agenda.length; j++) {
                    if (agenda[j] == null) { //a gasit primul loc liber, il scriem aici
                        Contact p = new Contact(nume, telefon);
                        agenda[j] = p;
                        System.out.println("Numele " + nume + " a fost introdus in agenda!");
                        break;
                    } else {
                        System.out.println("Ne pare rau, dar agenda este plina deja!");
                        break;
                    }
                }
            }
        }
        else {
            printConsole("Numele introdus exista deja.");
        }
    }

    //CAUTARE NUME IN AGENDA
    public static int cautare(String nume) {
        int r = -1;

        for (int i = 0; i < agenda.length; i++) {
            if (agenda[i]!=null && nume.equals(agenda[i].getNume())) {
                r = i;
                break; // il va inlocui pe primul pe care-l gaseste
            }
        }
        System.out.println();
        return r;
    }

    //CAUTARE NUME + AFISARE POZITIE
    public static int cautareAfisare(String nume) {
        int r = -1;
        for (int i = 0; i < agenda.length; i++) {
            if (agenda[i]!=null && nume.equals(agenda[i].getNume())) {
                r = i+1;
                printConsole("Numele " + nume + " a fost gasit pe pozitia " + r); //nu are break, deci le va afisa pe toate, daca se repeta
            }
        }
        if (r==-1){System.out.println("Numele " + nume + " nu a fost gasit in agenda!");}

        System.out.println();
        return r;
    }

    //MODIFICARE NUME IN AGENDA
    public static void modificare(String nume) {
        int index = cautare(nume); //indexul numelui de modificat
        if (index == -1) {
            printConsole("Numele " + nume + " nu exista in agenda!");
        } else {
            String numeModif = readStringConsole("Introduceti numele dorit: ");
            agenda[index].setNume(numeModif);  //inlocuim numele de la indexul respectiv cu numele nou
            printConsole("Nume modificat cu succes. Verificati prin afisare.");
        }
        System.out.println();
    }

    //STERGERE NUME
    public static void stergere(String nume) {
        int index = cautare(nume); //aflam indexul numelui de sters
        if (index == -1) {
            System.out.println();
            printConsole("Numele " + nume + " nu exista in agenda!");
            System.out.println();
        }
        else {
            agenda[index] = null;
            printConsole("Nume sters cu succes. Verificati prin afisare.");
        }
        System.out.println();
    }

}