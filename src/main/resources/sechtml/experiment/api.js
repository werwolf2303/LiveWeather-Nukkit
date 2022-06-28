class CheckAPI {
    available() {
        var api = false;
        var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
       // Typical action to be performed when the document is ready:
       api = true;
    }else{
        api = false;
    }
};
xhttp.open("GET", "/config", true);
xhttp.send();
        return api;
    }
}