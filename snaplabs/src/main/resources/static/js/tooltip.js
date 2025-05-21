export function showTooltip(event) {
    const url = event.currentTarget.getAttribute('data-src');
    const texto = event.currentTarget.getAttribute('data-desc');
    const coords = event.currentTarget.getBoundingClientRect();

    let tooltip = document.createElement("div");
    tooltip.classList.add('tooltip');
    tooltip.style.display = 'flex';
    tooltip.style.flexDirection = 'column';
    tooltip.style.width = '350px';
    tooltip.style.left = coords.left + (event.currentTarget.clientWidth/2 - 225) + "px";
    tooltip.style.top = coords.bottom + "px";

    let imagen = document.createElement("img");
    imagen.src = url;
    imagen.alt = "Arre demo, esto non furrula";
    imagen.classList.add('imagen');

    let descripcion = document.createElement("p");
    descripcion.textContent = texto;
    descripcion.style.padding = '0 2em';
    descripcion.style.textAlign = 'center';
    descripcion.style.fontFamily = 'IBM Plex Sans Condensed Regular';
    descripcion.style.fontSize = '1.25rem';
    descripcion.style.color = '#F5F1FB';

    tooltip.appendChild(imagen);
    tooltip.appendChild(descripcion);
    document.body.appendChild(tooltip);
}

export function deleteTooltip() {
    document.querySelectorAll(".tooltip").forEach(tooltip => tooltip.remove());
}