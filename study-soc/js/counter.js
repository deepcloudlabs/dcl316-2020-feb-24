let app = () => {
    let spanCounter = document.querySelector("#counter");
    let counter = 1;
    setInterval(() => {
        ++counter;
        spanCounter.innerHTML = counter;
    }, 1000);
}
window.onload = app;