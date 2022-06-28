function validate(key) {
window.location.href="login.html?key=" + key;
}
function error(message) {
console.err("ERROR: " + message);
}
function warning(message) {
console.warn("WARNING: " + message);
}
function info(message) {
console.log("INFO: " + message);
}
function init(body) {
info("Init...")
}