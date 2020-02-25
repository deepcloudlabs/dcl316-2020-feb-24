window.onload = () => {
    let updateView = () => {
        tries.innerHTML = game.tries;
        pbCounter.setAttribute("style",
            "width: " + ((5 * game.counter) / 3) + "%;");
        emptyElement(moves);
        for (let move of game.moves) {
            let row = moves.insertRow();
            let cellGuess = row.insertCell(0);
            let cellMessage = row.insertCell(1);
            cellGuess.appendChild(
                document.createTextNode(move.guess)
            );
            cellMessage.appendChild(
                document.createTextNode(move.message)
            );
        }
    }
    let game = new GameViewModel();
    let playBtn = document.querySelector("#play");
    let guess = document.querySelector("#guess");
    let tries = document.querySelector("#tries");
    let moves = document.querySelector("#moves");
    let pbCounter =
        document.querySelector("#pbCounter");
    setInterval(() => {
        game.countDown();
        updateView();
    }, 1000)
    playBtn.onclick =
        () => {
            game.play(guess.value);
            // update view
            updateView();
        }
};