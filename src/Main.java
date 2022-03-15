import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = null;

        try {
            sc = new Scanner(new File("employeesInput.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        ArrayList<Employee> employees = null;
        if (sc != null) {
            employees = getWork(sc);
        }
        if (employees != null) {
            getPair(employees);
        }
    }

    private static void getPair(ArrayList<Employee> employees) {
        Long maxDuration = 0L;
        int maxEmp1Id = 0;
        int maxEmp2Id = 0;
        for (int i = 0; i < employees.size(); i++) {
            for (int j = i+1; j < employees.size(); j++) {
                Employee emp1 = employees.get(i);
                Employee emp2 = employees.get(j);
                if(emp1.getLocalDateFrom().isAfter(emp2.getLocalDateFrom())){
                    emp1 = employees.get(j);
                    emp2 = employees.get(i);
                }

                if(emp1.getProjectID() == emp2.getProjectID()) {
                    if(emp2.getLocalDateFrom().isBefore(emp1.getLocalDateTo())) {
                        if(emp2.getLocalDateTo().isAfter(emp1.getLocalDateTo())) {
                            long duration = ChronoUnit.DAYS.between(emp2.getLocalDateFrom(), emp1.getLocalDateTo());
                            if (duration > maxDuration) {
                                maxDuration = duration;
                                maxEmp1Id = emp1.getEmpID();
                                maxEmp2Id = emp2.getEmpID();
                            }
                        } else {
                            long duration = ChronoUnit.DAYS.between(emp2.getLocalDateTo(),emp2.getLocalDateFrom());
                            if(duration > maxDuration){
                                maxDuration = duration;
                                maxEmp1Id = emp1.getEmpID();
                                maxEmp2Id = emp2.getEmpID();
                            }
                        }
                    }
                }
            }
        }

        printResults(maxEmp1Id, maxEmp2Id, maxDuration);
    }

    private static void printResults(int maxEmp1Id, int maxEmp2Id, Long maxDuration) {
        System.out.println(maxEmp1Id + " " + maxEmp2Id + " " + maxDuration);
    }

    private static ArrayList<Employee> getWork(Scanner sc) {
        ArrayList<Employee> employees = new ArrayList<>();
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(", ");
            Employee e = new Employee();
            e.setEmpID(Integer.parseInt(line[0]));
            e.setProjectID(Integer.parseInt(line[1]));
            e.setLocalDateFrom(LocalDate.parse(line[2]));
            e.setLocalDateTo(line[3].equalsIgnoreCase("NULL") ? LocalDate.now()
                    : LocalDate.parse(line[3]));
            employees.add(e);
        }
        return employees;
    }
}
