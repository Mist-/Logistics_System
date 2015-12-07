package data.po;


import data.enums.POType;

public class SalaryPO extends DataPO {
    private static final long serialVersionUID = 13;

    /*
    ���������ֱ��Ӧ нˮ�����š���н��ʽ���ƴΡ����£�
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
     ����ȫ��Ϊget��set����
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
