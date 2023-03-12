var b = document.getElementById("toggler");
b.addEventListener("click",action);


var hidden = false;
function action() {
    hidden = !hidden;
    if(hidden) {
        alert("are you sure to delete")
        document.getElementById('togglee').style.visibility = 'hidden';
    } else {
        document.getElementById('togglee').style.visibility = 'visible';
    }
}