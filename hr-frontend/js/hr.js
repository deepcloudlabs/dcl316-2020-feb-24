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
    update = (emp) => {
        for (let field in emp){
            let value = emp[field];
            if (field in this){
                if (ko.isObservable(this[field]))
                    this[field](value);
                else this[field] = value;
            }
        }
        if (this.photo() == null)
            this.photo(AppConfig.NO_IMAGE);
    }
}

class HrViewModel {
    constructor(){
        this.employee = new Employee();
        this.employees = ko.observableArray([]);
    }

    findAllEmps = () => {
        fetch(AppConfig.REST_API_BASE_URL
               +"/employees?page=0&size=25")
            .then( res => res.json())
            .then( emps => {
                this.employees(emps);
            });
    }

    findEmployee = () => {
        fetch(AppConfig.REST_API_BASE_URL
              +"/employees/"+this.employee.identity())
            .then( res => res.json())
            .then( emp => this.employee.update(emp));
    }
};