package aed;

//InvRep: |paresCarreraMateria| > 0

public class InfoMateria{

    private ParCarreraMateria[] paresCarreraMateria;

    public InfoMateria(ParCarreraMateria[] paresCarreraMateria){
        this.paresCarreraMateria = paresCarreraMateria;
    }

    public ParCarreraMateria[] getParesCarreraMateria() {
        return this.paresCarreraMateria;
    }
}
