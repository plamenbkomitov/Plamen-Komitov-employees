import java.time.LocalDate;

public class Employee {
    private int empID;
    private int projectID;
    private LocalDate locDateFrom;
    private LocalDate localDateTo;


    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public LocalDate getLocalDateFrom() {
        return locDateFrom;
    }

    public void setLocalDateFrom(LocalDate locDateFrom) {
        this.locDateFrom = locDateFrom;
    }

    public LocalDate getLocalDateTo() {
        return localDateTo;
    }

    public void setLocalDateTo(LocalDate dateTo) {
        this.localDateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", projectID=" + projectID +
                ", locDateFrom=" + locDateFrom +
                ", dateTo=" + localDateTo +
                '}';
    }
}
