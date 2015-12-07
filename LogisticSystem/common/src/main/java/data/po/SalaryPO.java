package data.po;


import data.enums.POType;

public class SalaryPO extends DataPO {
    private static final long serialVersionUID = 13;

    /*
    三个参数分别对应 薪水、部门、结薪方式（计次、按月）
     */
    private double salary;
    private String institution;
    private String type;

    public SalaryPO(double salary, String institution, String type) {
        super(POType.SALARY);
        this.salary = salary;
        this.institution = institution;
        this.type = type;
    }

     /*
     以下全部为get和set方法
      */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
