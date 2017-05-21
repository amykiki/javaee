function Person() {

}
Person.prototype.name = "Nicholas";
Person.prototype.age = 29;
Person.prototype.job = "Software Engineer";
Person.prototype.sayName = function () {
    console.log(this.name);
};

var person1 = new Person();
var person2 = new Person();

console.log(person1.name);
console.log(person2.name);

person1.sayName();

function Friend() {

}

Friend.prototype = {
    fname : "Greg",
    fage: 35,
    fjob: "Software Engineer",
    fSayName: function () {
        console.log(this.fname);
    }
};

var friend1 = new Friend();

function Friend2() {

}

var friend2 = new Friend2();
Friend2.prototype = {
    fname2 : "Greg",
    fage2: 35,
    fjob2: "Software Engineer",
    fSayName2: function () {
        console.log(this.fname2);
    }
};

function Person2(name, age, job) {
    this.name = name;
    this.age = age;
    this.job = job;
    if(typeof this.sayName != "function"){
        Person2.prototype.sayName = function () {
            console.log(this.name);
        };
    }
}

var person21 = new Person2("Amy", 30, "Web Developer");
person21.sayName();

function Person3(name, age, job) {
    var o = new Object();
    o.name = name;
    o.age = age;
    o.job = job;
    o.sayName = function () {
        console.log(this.name);
    };
    return o;
}
