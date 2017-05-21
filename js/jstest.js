console.log(sum1(10, 20));
function sum1(num1, num2) {
    return num1 + num2;
}
var sum2 = function (num1, num2) {
    return num1 + num2;
};
console.log(sum2(30, 40));

function factorial(num) {
    if(num <= 1) {
        return 1;
    }else {
        return num * arguments.callee(num - 1);
    }
}

console.log(factorial(5));
var trueFactorial = factorial;
factorial = function () {
    return 0;
}
console.log(trueFactorial(5));
console.log(factorial(5));

window.color = "red";
var obj1 = {
    color: "blue"
};

function sayColor() {
    console.log(this.color);
}

sayColor();
sayColor.call(this);
sayColor.call(window);
sayColor.call(obj1);

var obj2 = {
    color: "yellow"
};
var objSayColor = sayColor.bind(obj2);
objSayColor();
