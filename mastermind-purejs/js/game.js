class Move {
    constructor(guess, message) {
        this.guess = guess;
        this.message = message;
    }

}

class GameViewModel {
    constructor() {
        this.secret = createSecret();
        this.tries = 0;
        this.counter = 60;
        this.moves = [];
    }

    play = (guess) => {
        this.tries++;
        if (Number(guess) === this.secret) {
            this.initGame();
            this.moves.push(new Move(guess, "You win!"));
        } else {
            let message = this.createMessage(guess);
            this.moves.push(new Move(guess, message));
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
}