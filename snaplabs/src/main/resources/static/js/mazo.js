import { showTooltip, deleteTooltip } from './tooltip.js';

const imagenes = document.querySelectorAll(".gridCartas div");
const fecha = document.getElementById("fecha");
const arquetipos = document.querySelectorAll("input[name='arquetipo']");
const gridMazos = document.querySelector(".gridMazos");
const mazos = [...gridMazos.children];

imagenes.forEach(imagen => {
    imagen.addEventListener("mouseover", showTooltip);
    imagen.addEventListener("mouseleave", deleteTooltip);
});
fecha.addEventListener("change", ordenarMazosFecha);
arquetipos.forEach(checkbox => checkbox.addEventListener("change", filtrarMazoByArquetipo));

function ordenarMazosFecha(event) {
    gridMazos.innerHTML = "";
    const asc = event.target.value === "asc";
    mazos
        .sort((a,b) => {
            const valorA = a.getAttribute("data-fecha");
            const valorB = b.getAttribute("data-fecha");
            return asc ? new Date(valorA) - new Date(valorB) : new Date(valorB) - new Date(valorA);
        })
        .forEach(mazo => gridMazos.appendChild(mazo))
}

function filtrarMazoByArquetipo(event) {
    gridMazos.innerHTML = "";
    if (event.target.checked) {
        arquetipos.forEach(checkbox => {
            if (event.target !== checkbox && checkbox.checked) checkbox.checked = false;
        });
        mazos
            .filter(mazo => mazo.getAttribute("data-arquetipo") === event.target.value)
            .forEach(mazo => gridMazos.appendChild(mazo));
    } else {
        mazos.forEach(mazo => gridMazos.appendChild(mazo));
    }
}