public class Livre {
    private String libelle;
    private String auteur;
    private String date;
    private String code;
    private Boolean disponible;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "libelle='" + libelle + '\'' +
                ", auteur='" + auteur + '\'' +
                ", date='" + date + '\'' +
                ", code='" + code + '\'' +
                ", disponible=" + disponible +
                ", ID=" + ID +
                '}';
    }
}
