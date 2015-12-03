package data.vo;

public class SalaryVO {
    /*
     三个参数分别对应 薪水、部门、结薪方式（计次、按月）
      */
    public double salary;
    public String institution;
    public String type;

    public SalaryVO(String institution, double salary, String type) {
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
