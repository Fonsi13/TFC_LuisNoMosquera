import { showTooltip, deleteTooltip } from './tooltip.js';

const imagenes = document.querySelectorAll(".gridCartas div");

imagenes.forEach(imagen => {
    imagen.addEventListener("mouseover", showTooltip);
    imagen.addEventListener("mouseleave", deleteTooltip);
});

function copiarCodigo(event) {
    codigo = event.target.value;
    navigator.clipboard.writeText(codigo);
    alert("Código copiado");
}
