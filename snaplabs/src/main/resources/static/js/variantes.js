const gridVariantes = document.querySelector(".grid-variantes");
const listaCartas = document.querySelectorAll(".carta");
const selectFecha = document.getElementById("fecha");
const selectLikes = document.getElementById("likes");

selectFecha.addEventListener("change", ordenarByFecha);
selectLikes.addEventListener("change", ordenarByLikes);

function ordenarByFecha(event) {
    const primero = [...gridVariantes.children].shift();
    const asc = event.target.value === "asc";

    gridVariantes.innerHTML = "";
    gridVariantes.appendChild(primero)
    Array.from(listaCartas)
        .sort((a,b) => {
        const valorA = a.getAttribute("data-fecha");
        const valorB = b.getAttribute("data-fecha");
        return asc ? new Date(valorA) - new Date(valorB) : new Date(valorB) - new Date(valorA);
    })
        .forEach(variante => gridVariantes.appendChild(variante));
}

function ordenarByLikes(event) {
    const primero = [...gridVariantes.children].shift();
    const asc = event.target.value === "asc";

    gridVariantes.innerHTML = "";
    gridVariantes.appendChild(primero)
    Array.from(listaCartas)
        .sort((a,b) => {
        const valorA = a.getAttribute("data-likes");
        const valorB = b.getAttribute("data-likes");
        return asc ? parseInt(valorA) - parseInt(valorB)  : parseInt(valorB) - parseInt(valorA);
    })
        .forEach(variante => gridVariantes.appendChild(variante));
}
