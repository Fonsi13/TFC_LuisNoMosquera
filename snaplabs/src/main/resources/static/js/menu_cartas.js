const inputBusqueda = document.getElementById("buscar");
const alfabetico = document.getElementById("alfa");
const coste = document.getElementById("coste");
const poder = document.getElementById("poder");
const checkboxes = document.querySelectorAll("input[type='checkbox']");
const grid = document.querySelector(".gridCartas");
const listaCartas = [...grid.children];

alfabetico.addEventListener("change", (event) => ordenarCartasAlf(event));
coste.addEventListener("change", (event) => ordenarCartasDato(event));
poder.addEventListener("change", (event) => ordenarCartasDato(event));
inputBusqueda.addEventListener("input", (event) => buscarBy(event));
checkboxes.forEach(checkbox => {
    checkbox.addEventListener("change", filtrarCartasHabilidad);
});

function ordenarCartasAlf() {
    grid.innerHTML = "";
    asc = event.target.value === "asc";
    listaCartas
        .sort((a, b) => asc ? a.id.localeCompare(b.id) : b.id.localeCompare(a.id))
        .forEach(node => grid.appendChild(node));
}

function ordenarCartasDato(event) {
    grid.innerHTML = "";
    criterio = event.target.id;
    asc = event.target.value === "asc";
    listaCartas
        .sort((a, b) => {
            const valorA = parseInt(a.getAttribute(`data-${criterio}`));
            const valorB = parseInt(b.getAttribute(`data-${criterio}`));
            return asc ? valorA - valorB : valorB - valorA;
        })
        .forEach(node => grid.appendChild(node));
}

function filtrarCartasHabilidad() {
    grid.innerHTML = "";
    var habilidadesChecked = [];
    var checked = document.querySelectorAll('input[type=checkbox]:checked');
    for (let i = 0; i < checked.length; i++) {
        habilidadesChecked.push(checked[i].value);
    }
    if (habilidadesChecked.length > 0) {
        const listaFiltrada = listaCartas.filter((item) => {
            if (item.getAttribute(`data-habilidades`) == null) return false;
            const habilidadesCarta = item.getAttribute(`data-habilidades`).split(",");
            return habilidadesChecked.every(habilidad => habilidadesCarta.includes(habilidad));
        });
        listaFiltrada.forEach(node => grid.appendChild(node));
    } else {
        listaCartas.forEach(node => grid.appendChild(node));
    }
}

function buscarBy(event) {
    grid.innerHTML = "";
    const textoBusqueda = event.target.value.toLowerCase();
    const listaFiltrada = listaCartas.filter((item) =>
        item.id.toLowerCase().includes(textoBusqueda)
    );
    listaFiltrada.forEach(node => grid.appendChild(node));
}