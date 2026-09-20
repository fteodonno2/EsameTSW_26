package model.beans;

public class User {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;
    private boolean isAdmin;

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
