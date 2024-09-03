var popup = document.getElementById("popup");
var closePopupBtn = document.getElementById("closePopupBtn");
var closeBtn = document.querySelector(".close-btn");

$(document).on('click','.view',function() {
    popup.style.display = "block";
})

closeBtn.onclick = function() {
    popup.style.display = "none";
}

$(document).on('click','#closePopupBtn',function(){
	popup.style.display = "none";
})

window.onclick = function(event) {
    if (event.target == popup) {
        popup.style.display = "none";
    }
}