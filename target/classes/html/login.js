var api = false;
function validate(key) {
if(api) {
    if(!key=="") {
window.location.href="login.html?key=" + key;
    }else{
        alert("!!Password cant be empty!!");
    }
}else{
    alert("!!Cant authenticate!! API Offline")
}
}
function error(message) {
console.error("ERROR: " + message);
}
function warning(message) {
console.warn("WARNING: " + message);
}
function info(message) {
console.log("INFO: " + message);
}
function init() {
info("Init...");
this.mainform = document.getElementById("mainform");
if(!this.mainform) {
    error("!!Failed init!! Could not assign this.mainform");
}
$('#mainform')
	.draggable()
	.resizable();
this.mainform.addEventListener('mousedown',onClickMainformDown, false);
this.mainform.addEventListener('mouseup',onClickMainformUp, false);
setCentered(this.mainform);
window.onresize = setCentered(this.mainform);
var oReq = new XMLHttpRequest();
oReq.open("GET", "config");
oReq.send();
oReq.onload = function () {
    if(oReq.status==404) {
        document.getElementById("error").style.display="";
    }else{
        api = true;
    }
};
}
function onClickMainformDown() {
    document.body.style.cursor = "move";
}
function onClickMainformUp() {
    document.body.style.cursor = "default";
}
function setCentered(div) {
    var x = window.innerWidth / 2;
    div.style.top=x+"px";
}
