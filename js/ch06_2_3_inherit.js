/**
 * Created by zoush on 2017/5/21.
 */
// instanceof 运算符用来检测 constructor.prototype 是否存在于参数 object 的原型链上。
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

SubType1.prototype = new SuperType1();
SubType1.prototype.constructor = SubType1;

SubType1.prototype.sayAge = function () {
    return this.age;
};

var sub1 = new SubType1("AMY", 20);

var sub2 = new SubType1("Kevin", 32);

