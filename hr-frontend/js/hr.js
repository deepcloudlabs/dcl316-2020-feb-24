class Employee {
    constructor() {
        this.birthYear	= ko.observable(2000);
        this.department	= ko.observable("IT");
        this.fullname = ko.observable();
        this.fulltime = ko.observable(true);
        this.iban = ko.observable("TR");
        this.identity = ko.observable("1");
        this.photo =
            ko.observable(AppConfig.NO_IMAGE);
        this.salary = ko.observable(2700);
    }
}

class HrViewModel {
    constructor(){
        this.employee = new Employee();
    }
};