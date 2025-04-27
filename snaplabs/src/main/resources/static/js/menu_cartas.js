const inputBusqueda = document.getElementById("buscar");
const alfabetico = document.getElementById("alfa");
const coste = document.getElementById("coste");
const poder = document.getElementById("poder");
const grid = document.querySelector(".gridCartas");
const listaCartas = Array.from(grid.children);

alfabetico.addEventListener("change", () => ordenarCartasAlf());
coste.addEventListener("change", (event) => ordenarCartasDato(event));
poder.addEventListener("change", (event) => ordenarCartasDato(event));
inputBusqueda.addEventListener("input", (event) => buscarBy(event));

function ordenarCartasAlf() {
    grid.innerHTML = "";
    asc = (alfabetico.value === "asc") ? true : false;
    listaCartas
        .sort((a, b) => asc ? a.id.localeCompare(b.id) : b.id.localeCompare(a.id))
        .forEach(node => grid.appendChild(node));
}

function ordenarCartasDato(event) {
    grid.innerHTML = "";
    criterio = event.target.id;
    asc = (event.target.value === "asc") ? true : false;
    listaCartas
        .sort((a, b) => {
            const valorA = parseInt(a.getAttribute(`data-${criterio}`));
            const valorB = parseInt(b.getAttribute(`data-${criterio}`));
            return asc ? valorA - valorB : valorB - valorA;
        })
        .forEach(node => grid.appendChild(node));
}

function buscarBy(event) {
    grid.innerHTML = "";
    const textoBusqueda = event.target.value.toLowerCase();
    const listaFiltrada = listaCartas.filter((item) =>
        item.id.toLowerCase().includes(textoBusqueda)
    );
    listaFiltrada.forEach(node => grid.appendChild(node));
}