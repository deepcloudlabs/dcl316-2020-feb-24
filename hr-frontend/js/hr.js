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
    addEmployee = () => {
        let emp = ko.toJS(this.employee);
        emp.photo = toRawImage(emp.photo);
        fetch(AppConfig.REST_API_BASE_URL + "/employees", {
            method: "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(emp)
        }).then( res => res.json())
          .then( emp => toastr.success("Employee is added"));

    }

    updateEmployee = () => {
        let emp = ko.toJS(this.employee);
        emp.photo = toRawImage(emp.photo);
        fetch(AppConfig.REST_API_BASE_URL + "/employees/"+this.employee.identity(), {
            method: "PUT",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(emp)
        }).then( res => res.json())
          .then( emp => toastr.success("Employee is updated"));

    }

    removeEmployee = () => {
        fetch(AppConfig.REST_API_BASE_URL + "/employees/"+this.employee.identity(), {
            method: "DELETE"
        }).then( res => res.json())
          .then( emp => {
              toastr.success("Employee is deleted");
              if (emp.photo == null)
                  emp.photo = AppConfig.NO_IMAGE;
              this.employee.update(emp);
          });
    }

    findAllEmps = () => {
        fetch(AppConfig.REST_API_BASE_URL
               +"/employees?page=0&size=25")
            .then( res => res.json())
            .then( emps => {
                emps.forEach( emp => {
                    if (emp.photo == null)
                        emp.photo = AppConfig.NO_IMAGE;
                    else
                        emp.photo = toSrcImage(emp.photo)
                })
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