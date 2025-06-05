const nombre = document.getElementById("hiddenUsername").value;
const correo = document.getElementById("hiddenEmail").value;

function resetCampos() {
    document.getElementById("username").value = nombre;
    document.getElementById("email").value = correo;
}