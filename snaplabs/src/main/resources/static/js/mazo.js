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

function showTooltip(event) {
    const url = event.currentTarget.getAttribute('data-src');
    const texto = event.currentTarget.getAttribute('data-desc');
    const coords = event.currentTarget.getBoundingClientRect();

    let tooltip = document.createElement("div");
    tooltip.classList.add('tooltip');
    tooltip.style.display = 'flex';
    tooltip.style.flexDirection = 'column';
    tooltip.style.width = '450px';
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

function deleteTooltip() {
    document.querySelectorAll(".tooltip").forEach(tooltip => tooltip.remove());
}

function ordenarMazosFecha(event) {
    gridMazos.innerHTML = "";
    asc = event.target.value === "asc";
    mazos
        .sort((a,b) => {
        valorA = a.getAttribute("data-fecha");
        valorB = b.getAttribute("data-fecha");
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

function copiarCodigo(event) {
    codigo = event.target.value;
    navigator.clipboard.writeText(codigo);
    alert("Código copiado");
}