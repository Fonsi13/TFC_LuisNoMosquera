import { showTooltip, deleteTooltip } from './tooltip.js';

const imagenes = document.querySelectorAll(".gridCartasTabla div");

imagenes.forEach(imagen => {
    imagen.addEventListener("mouseover", showTooltip);
    imagen.addEventListener("mouseleave", deleteTooltip);
});