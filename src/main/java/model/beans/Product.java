package model.beans;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String nome;
    private String descrizione;
    private BigDecimal prezzo;
    private String categoria;
    private String immagine;

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getImmagine() { return immagine; }
    public void setImmagine(String immagine) { this.immagine = immagine; }
}
