// setInterval( () => {game.guess(createSecret()+""); game.play();},5)
let game = new GameViewModel();
$(document).ready(() => {
 ko.applyBindings(game);
 setInterval(game.countDown, 1000);
});