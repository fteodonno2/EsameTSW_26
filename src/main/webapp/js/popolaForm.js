 function popolaForm(id, nome, descrizione, prezzo, categoria, immagine) {
    document.getElementById("id").value = id;
    document.getElementById("nome").value = nome;
    document.getElementById("descrizione").value = descrizione;
    document.getElementById("prezzo").value = prezzo;
    document.getElementById("categoria").value = categoria;
    document.getElementById("immagine").value = immagine;

    document.getElementById("editForm").scrollIntoView({ behavior: "smooth" });
}