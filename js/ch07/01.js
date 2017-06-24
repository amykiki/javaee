/**
 * Created by zoushumin on 2017/6/4.
 */
var factorial = (function f(num) {
    if( num <= 1) {
        return 1;
    }else {
        return num * f(num - 1);
    }

});

console.log(factorial(5));

var anotherFactorial = factorial;
factorial = null;
console.log(anotherFactorial(6));