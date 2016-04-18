/**
 * Created by Manu on 18-Apr-16.
 */
//Clasa mea de obiecte
public class Contact {
    private String nume;
    private String telefon;

    //GETTER nume
    public String getNume() {
        return nume;
    }

    //SETTER nume
    public void setNume(String nume) {
        this.nume = nume;
    }

    //GETTER telefon
    public String getTelefon() {
        return telefon;
    }

    //SETTER telefon
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    //CONSTRUCTOR DE OBIECTE (contacte in agenda)
    public Contact(String n, String t){
        nume=n;
        telefon=t;
    }
}
