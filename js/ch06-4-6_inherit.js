/**
 * Created by zoush on 2017/5/21.
 */
function objectInhrient(o) {
    function F() {}
    F.prototype = o;
    return new F();
}

var person1 = {
    name: "AmyStar",
    friends: ["Shelby", "Court", "Van"]
};

var anotherPerson = objectInhrient(person1);
var yetAnotherPerson = objectInhrient(person1);


function inheritPrototype(subType, superType) {
    var prototype = objectInhrient(superType.prototype);
    prototype.constructor = subType;
    subType.prototype = prototype;
}
function SuperType1(name) {
    this.name = name;
    this.colors = ["red", "blue", "green"];
}

SuperType1.prototype = {
    sayName: function () {
        return this.name;
    }
};
function SubType1(name, age) {
    SuperType1.call(this, name);
    this.age = age;
}

inheritPrototype(SubType1, SuperType1);
SubType1.prototype.sayAge = function () {
    return this.age;
};

var sub1 = new SubType1("Amy", 16);

