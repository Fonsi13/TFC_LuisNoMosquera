import { showTooltip, deleteTooltip } from './tooltip.js';

const imagenes = document.querySelectorAll(".gridCartas div");

imagenes.forEach(imagen => {
    imagen.addEventListener("mouseover", showTooltip);
    imagen.addEventListener("mouseleave", deleteTooltip);
});