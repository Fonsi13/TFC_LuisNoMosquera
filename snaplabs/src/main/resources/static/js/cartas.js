const inputBusqueda = document.getElementById("buscar");
const alfabetico = document.getElementById("alfa");
const coste = document.getElementById("coste");
const poder = document.getElementById("poder");
const checkboxes = document.querySelectorAll(".filtros input[type='checkbox']");
const grid = document.querySelector(".gridCartas");
const listaCartas = [...grid.children];
const listaCartasLab = document.querySelectorAll(".cartaLab");
const gridBuilder = document.querySelector(".gridBuilder");
const cartaVacia = document.querySelector(".void");
const formulario = document.querySelector("#formBuilder");
var cartasMazo = [];

alfabetico.addEventListener("change", ordenarCartasAlf);
coste.addEventListener("change", ordenarCartasDato);
poder.addEventListener("change", ordenarCartasDato);
inputBusqueda.addEventListener("input", buscarBy);
checkboxes.forEach(checkbox => {
    checkbox.addEventListener("change", filtrarCartasHabilidad);
});
formulario?.addEventListener("submit", validarFormulario);
listaCartasLab.forEach(carta => {
    carta.addEventListener("click", addCartaBuilder);
});

function ordenarCartasAlf() {
    grid.innerHTML = "";
    const asc = event.target.value === "asc";
    listaCartas
        .sort((a, b) => asc ? a.id.localeCompare(b.id) : b.id.localeCompare(a.id))
        .forEach(node => grid.appendChild(node));
}

function ordenarCartasDato(event) {
    grid.innerHTML = "";
    criterio = event.target.id;
    const asc = event.target.value === "asc";
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
    const listaFiltrada = listaCartas.filter((item) => item.id.toLowerCase().includes(textoBusqueda));
    listaFiltrada.forEach(node => grid.appendChild(node));
}

function filtrarCartasHabilidad() {
    grid.innerHTML = "";
    var habilidadesChecked = [];
    var checked = document.querySelectorAll('.filtros input[type=checkbox]:checked');
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

function addCartaBuilder(event) {
    const listaCartasBuilder = [...gridBuilder.children];
    const cartasVacias = listaCartasBuilder.filter(carta => carta.classList.contains("void"));
    const cartasSeleccionadas = listaCartasBuilder.filter(carta => carta.classList.contains("selected"));
    if (cartasVacias.length > 0 && cartasSeleccionadas.length < 12) {
        const nuevaCarta = event.currentTarget.cloneNode(true);
        nuevaCarta.style.backgroundImage = `url('${nuevaCarta.querySelector("img").src}')`;
        nuevaCarta.classList.remove("carta");
        nuevaCarta.classList.remove("cartaLab");
        nuevaCarta.classList.add("selected");
        [...nuevaCarta.children].forEach(node => node.remove());
        nuevaCarta.addEventListener("click", removeCartaBuilder);

        cartasVacias[0].replaceWith(nuevaCarta);
        event.currentTarget.removeEventListener("click", addCartaBuilder);
        event.currentTarget.classList.add("disabled");
    }
}

function removeCartaBuilder(event) {
    carta = [...listaCartasLab].find(carta => carta.id === event.currentTarget.id);
    carta.addEventListener("click", addCartaBuilder);
    carta.classList.remove("disabled");
    const clonVacio = cartaVacia.cloneNode(true);
    event.currentTarget.replaceWith(clonVacio);
}

function validarFormulario() {
    cartas = [];
    document.querySelectorAll(".selected").forEach(carta => cartas.push(carta.id));
    if (cartas.length == 12)
        document.querySelector("#contenido").value = cartas.join(',');
    else
        document.querySelector("#contenido").value = '';
    formulario.submit();
}

