var api = false;
function error(message) {
    console.error("ERROR: " + message);
}
function warning(message) {
    console.warn("WARNING: " + message);
}
function info(message) {
    console.log("INFO: " + message);
}
var mainform = "";
function init() {
    info("Init...");
    this.mainform = document.getElementById("mainform");
    if(!this.mainform) {
        error("!!Failed init!! Could not assign this.mainform");
    }
    $('#mainform')
        .draggable().resizable();
    this.mainform.addEventListener('mousedown',onClickMainformDown, false);
    this.mainform.addEventListener('mouseup',onClickMainformUp, false);
    setCentered(this.mainform);
    window.onresize = setCentered(this.mainform);
            $("#nnav").load("header.html");
            mainform = this.mainform;
            new ResizeObserver(onResize).observe(this.mainform);

            var oReq = new XMLHttpRequest();
oReq.open("GET", "/config");
oReq.send();
oReq.onload = function () {
    if(oReq.status==404) {
        document.getElementById("error").style.display="";
    }else{
        api = true;
    }
};
initFunctions();
}
function onClickMainformDown() {
        document.body.style.cursor = "move";
}
function onClickMainformUp() {
        document.body.style.cursor = "default";
}
function setCentered(div) {
        var x = window.innerWidth / 2 /2;
        div.style.top=x+"px";
}
function onResize() {
    if(mainform.offsetHeight<398) {
        mainform.style.height = "422px";
    }
    if(mainform.offsetWidth<423) {
        mainform.style.width = "423px";
    }
}
function getValue(id) {
    if(api) {
    var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "/config?" + id, false ); // false for synchronous request
xmlHttp.send( null );
return xmlHttp.responseText;
    }else{
        alert("!!Cant get value!! API Offline");
    }
  }
function initFunctions() {
  var checkboxes = document.querySelectorAll("input[type=checkbox]");
checkboxes.forEach(function(checkbox) {
checkbox.addEventListener('change', function() {
var checked = checkbox.checked;
var name = checkbox.checked;
if(checked==0) {
checked = false;
}else{
checked = true;
}
if(api){
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "/config?" + name + "=" + checked, false ); // false for synchronous request
xmlHttp.send( null );
return xmlHttp.responseText;
}else{
    alert("!!Cant send to server!! API Offline");
}
});
});
}
