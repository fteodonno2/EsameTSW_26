    const btn = document.getElementById("categorieDropdownBtn");
    const dropdown = document.getElementById("categorieDropdown");

    btn?.addEventListener("click", (e) => {
    e.preventDefault();
    dropdown.classList.toggle("show");
});

    document.addEventListener("click", (event) => {
    if (!btn.contains(event.target) && !dropdown.contains(event.target)) {
    dropdown.classList.remove("show");
}
});

    function submitCategoria(categoria) {
    const input = document.getElementById("categoriaInput");
    input.value = categoria;
    document.getElementById("categoriaForm").submit();
}

