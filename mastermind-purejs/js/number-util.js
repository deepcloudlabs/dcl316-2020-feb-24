let createNumber = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

let hasDuplicateDigits = (number) => {
    var digits = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
    number = number.toString();
    for (var i = 0; i < number.length; ++i) {
        var digit = new Number(number.charAt(i));
        digits[digit]++;
    }
    for (var i = 0; i < digits.length; ++i) {
        if (digits[i] > 1) return true;
    }
    return false;
}

let createSecret = () => {
    var candidate;
    do {
        candidate = createNumber(102, 987);
    } while (hasDuplicateDigits(candidate)) ;
    return candidate;
}

let emptyElement = (element) => {
    var node = element;
    while (element.hasChildNodes()) {
        if (node.hasChildNodes()) {
            node = node.lastChild;
        } else {
            node = node.parentNode;
            node.removeChild(node.lastChild);
        }
    }
};