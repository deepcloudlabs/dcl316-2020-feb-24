class Move {
    constructor(guess, message) {
        this.guess = guess;
        this.message = message;
    }
}

class GameViewModel {
    constructor() {
        this.secret = createSecret();
        this.tries = ko.observable(0);
        this.counter = ko.observable(60);
        this.moves = ko.observableArray([]);
        this.guess = ko.observable("123");
    }

    play = () => {
        this.tries(this.tries() + 1);
        if (Number(this.guess()) === this.secret) {
            this.initGame();
            this.moves.push(new Move(this.guess(), "You win!"));
        } else {
            let message = this.createMessage(this.guess());
            this.moves.push(new Move(this.guess(), message));
        }
    }

    createMessage = (guess) => {
        let secret = this.secret.toString();
        let perfectMatch = 0;
        let partialMatch = 0;
        for (let i = 0; i < guess.length; ++i) {
            let g = guess.charAt(i);
            for (let j = 0; j < secret.length; ++j) {
                let s = secret.charAt(j);
                if (s === g) {
                    if (i === j) {
                        perfectMatch++;
                    } else {
                        partialMatch++;
                    }
                }
            }
        }
        if (perfectMatch === 0 && partialMatch === 0)
            return "No match!";
        let message = "";
        if (partialMatch > 0)
            message = `-${partialMatch}`;
        if (perfectMatch > 0)
            message += "+" + perfectMatch;
        return message;
    }

    initGame = () => {
        this.moves([]);
        this.tries(0);
        this.counter(60);
        this.secret = createSecret();
    }

    countDown = () => {
        this.counter(this.counter() - 1);
        if (this.counter() <= 0) {
            let move =
                new Move(this.secret, "You lose!");
            this.initGame();
            this.moves.push(move);
        }
    }
}