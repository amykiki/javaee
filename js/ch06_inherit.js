/**
 * Created by zoush on 2017/5/21.
 */

function SuperType1() {
    this.superName = "SUPER1";
    this.colors = ["red", "blue", "green"];
}

SuperType1.prototype.getSuperName = function () {
    return this.superName;
};

function SubType1() {
    this.subName = "SUB1";
}

SubType1.prototype = new SuperType1();

SubType1.prototype.getSubName = function () {
    return this.subName;
};

var sub1 = new SubType1();

function SubType2() {
    this.subName = "SUB2";
}

SubType2.prototype = new SuperType1();

SubType2.prototype.getSubName = function () {
    return this.subName;
};

SubType2.prototype.getSuperName = function () {
    return "SUPER_SUB2";
};

var sub2 = new SubType2();


