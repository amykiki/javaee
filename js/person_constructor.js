function Person(name, age, job) {
    this.name = name;
    this.age = age;
    this.job = job;
    this.sayName = function () {
        console.log(this.name);
    };
}

var person1 = new Person("Amy", 30, "Software Engineer");
var person2 = new Person("Kevin", 32, "Product Manager");

person1.sayName();
person2.sayName();

console.log(person1.constructor == Person);
console.log(person2.constructor == Person);

console.log(person1 instanceof Person);
console.log(person2 instanceof Person);

var obj2 = new Object();
Person.call(obj2, "Kristen", 25, "Nurse");
obj2.sayName();