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
        this.pbWidth = ko.computed(() => {
            return ((5 * this.counter()) / 3) + "%";
        });
        this.pbClass = ko.computed(() => {
            if (this.counter() <= 10)
                return "progress-bar progress-bar-danger";
            else if (this.counter() <= 20)
                return "progress-bar progress-bar-warning";
            return "progress-bar progress-bar-striped";
        });
        this.wins = ko.observable(0);
        this.loses = ko.observable(0);
        this.total = ko.computed(() => {
            return this.wins() + this.loses();
        })
        this.totalMoves = ko.observable(0);
        this.avgMoves = ko.computed(() => {
            if (this.wins() === 0) return "N/A";
            return this.totalMoves() / this.wins();
        });
        this.totalWinTime = ko.observable(0);
        this.avgWinTime = ko.computed(() => {
            if (this.wins() === 0) return "N/A";
            return this.totalWinTime() / this.wins();
        });

    }

    play = () => {
        this.tries(this.tries() + 1);
        if (Number(this.guess()) === this.secret) {
            this.wins(this.wins() + 1);
            this.totalMoves(this.totalMoves() + this.tries());
            this.totalWinTime(this.totalWinTime() + 60 - this.counter());
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
            let move = new Move(this.secret, "You lose!");
            this.initGame();
            this.moves.push(move);
            this.loses(this.loses() + 1);
        }
    }
}