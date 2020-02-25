$(document).ready(() => {
    let updateTable = () => {
        $("#moves").empty();
        for (let move of game.moves) {
            $("#moves").append(`<tr><td>${move.guess}</td><td>${move.message}</td></tr>`)
        }
    }
    let updateView = () => {
        $("#tries").text(game.tries);
        $("#pbCounter").css("width", ((5 * game.counter) / 3) + "%");
        updateTable();
    }
    let game = new GameViewModel();
    setInterval(() => {
        game.countDown();
        updateView();
    }, 1000)
    $("#play").click(() => {
        game.play($("#guess").val());
        updateView();
    });
});