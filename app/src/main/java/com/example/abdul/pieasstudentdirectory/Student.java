package com.example.abdul.pieasstudentdirectory;

import java.util.*;
import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Formatter formatter = new Formatter(System.out);

    private String name;
    private String regNo;
    private String department;
    private String phoneNo;
    private String gender;
//    private ImageIcon imageIcon;

    // Constructors
    public Student() {
        this("", "", "", "00000000000", "-");
    }

    public Student(Student std) {
        this(std.name, std.regNo, std.department, std.phoneNo, std.gender);
    }

    public Student(String name, String regNo, String department, String phoneNo, String gender) {
        setName(name);
        setRegNo(regNo);
        setDepartment(department);
        setPhoneNo(phoneNo);
        setGender(gender);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRegNo(String regNo) {
        this.regNo = createValidFormatRegNo(regNo);
    }

    public void setDepartment(String department) {
		this.department = createValidFormatDepartment(department);
    }

    public void setPhoneNo(String phoneNo) {
		this.phoneNo = createValidFormatPhoneNo(phoneNo);
//        this.phoneNo = phoneNo;
    }

    public void setGender(String gender) {
        this.gender = createValidFormatGender(gender);
    }

//    public void setImageIcon(String icon) {
//        this.imageIcon = new ImageIcon(icon);
//    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getGender() {
        return gender;
    }

//    public ImageIcon getImageIcon() {
//        return imageIcon;
//    }

    public void showStudent() {
        System.out.println("--------------------------------------------------");
        formatter.format("%25s", "Name -> ");
        formatter.format("%25s", name);
        System.out.println();
        formatter.format("%25s", "Registration# -> ");
        formatter.format("%25s", regNo);
        System.out.println();
        formatter.format("%25s", "Department -> ");
        formatter.format("%25s", department);
        System.out.println();
        formatter.format("%25s", "Phone#-> ");
        formatter.format("%25s", phoneNo);
        System.out.println();
        formatter.format("%25s", "Gender -> ");
        formatter.format("%25s", gender);
        System.out.println();
        formatter.format("%25s", "ImageIcon -> ");
//        formatter.format("%25s", imageIcon.toString());
        System.out.println();
        // formatter.format("%25s", imageIcon);System.out.println();
        System.out.println("--------------------------------------------------");
    }

    // Override Method
    @Override
    public String toString() {
        return "\n------------------------------" + "\n" + "Name -> " + this.name + "\n" + "Registration# -> "
                + this.regNo + "\n" + "Department -> " + this.department + "\n" + "Phone# -> " + phoneNo + "\n"
                + "Gender -> " + this.gender + "\n" + "------------------------------\n";
    }

    @Override
    public boolean equals(Object std) {
        Student student = (Student) std;
        return this.name.equals(student.getName()) &&
                this.regNo.equals(student.getRegNo()) &&
                this.department.equals(student.getDepartment()) && this.phoneNo.equals(student.getPhoneNo()) &&
                this.gender.equals(student.getGender());
    }

    // Static Methods
    public static String parseStudentToString(Student std) {
        return std.name + ";" + std.regNo + ";" + std.department + ";" + std.phoneNo + ";" + std.gender + ";";
    }

    public static Student parseStringToStudent(String line) {
        String name = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String regNo = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String department = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String phoneNo = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String gender = line.substring(0, line.indexOf(";"));

        return new Student(name, regNo, department, phoneNo, gender);
    }

    public static void showStudents(List<Student> students) {
        for (Student s : students) {
            s.showStudent();
        }
    }

    public static void sortByRegNo(List<Student> students) {
        Student temp;
        for (int i = 0; i < students.size(); i++) {
            for (int j = 1; j < students.size() - i; j++) {
                if (students.get(j - 1).getRegNo().equals(students.get(j).getRegNo())) {
                    temp = new Student(students.get(j - 1));
                    students.set(j - 1, students.get(j));
                    students.set(j, temp);
                }
            }
        }
    }

    public static Student searchStudentByRegNo(List<Student> students, String rNo) {
        for (Student s : students) {
            if (s.getRegNo().equals(rNo)) {
                return s;
            }
        }
        return null;
    }

    public static Object[][] create2DData(List<Student> students) {
        Object[][] data2D = new Object[students.size()][7];
        for (int i = 0; i < students.size(); i++) {
            data2D[i][0] = Integer.toString(i + 1);
            data2D[i][1] = students.get(i).getRegNo();
            data2D[i][2] = students.get(i).getName();
            data2D[i][3] = students.get(i).getDepartment();
            data2D[i][4] = students.get(i).getPhoneNo();
            data2D[i][5] = students.get(i).getGender();
//            data2D[i][6] = students.get(i).getImageIcon();
        }
        return data2D;
    }

    public static String createValidFormatRegNo(String regNo) {
        String oldRegNo = removeChar(regNo, ' ');
        oldRegNo = removeChar(oldRegNo, '-');
        String newRegNo = "";
        int count = 0;
        if (isValidRegNo(regNo)) {
            for (int i = 0; i < 15; i++) {
                if (i == 2 || i == 4 || i == 6 || i == 10) {
                    newRegNo += '-';
                } else {
                    newRegNo += oldRegNo.charAt(count);
                    count++;
                }
            }
        }
        return newRegNo;
    }

    public static boolean isValidRegNo(String regNo) {
        regNo = removeChar(regNo, ' ');
        boolean result = false;
        if (regNo.length() < 11 || regNo.length() > 15) {
//			System.out.println(1);
            result = false;
        } else if (regNo.length() == 11 && countChar(regNo, '-') == 0 && countInts(regNo) == 11) {
//			System.out.println(2);
            result = true;
        } else if (regNo.length() == 12 && countChar(regNo, '-') == 1 && countInts(regNo) == 11) {
//			System.out.println(3);
            int location1 = regNo.indexOf('-');
            if (location1 == 2 || location1 == 3 || location1 == 4 || location1 == 7) {
                result = true;
            }
        } else if (regNo.length() == 13 && countChar(regNo, '-') == 2 && countInts(regNo) == 11) {
//			System.out.println(4);
            int location1 = regNo.indexOf('-');
            int location2 = regNo.substring(location1 + 1).indexOf('-') + location1 + 1;
            if (location1 == 2) {
                if (location2 == 4 || location2 == 5 || location2 == 8) {
                    result = true;
                }
            } else if (location1 == 3) {
                if (location2 == 5 || location2 == 8) {
                    result = true;
                }
            } else if (location1 == 4) {
                if (location2 == 8) {
                    result = true;
                }
            }
        } else if (regNo.length() == 14 && countChar(regNo, '-') == 3 && countInts(regNo) == 11) {
//			System.out.println(5);
            int location1 = regNo.indexOf('-');
            int location2 = regNo.substring(location1 + 1).indexOf('-') + location1 + 1;
            int location3 = regNo.substring(location2 + 1).indexOf('-') + location2 + 1;
//			System.out.println("location1 -> " + location1);
//			System.out.println("location2 -> " + location2);
//			System.out.println("location3 -> " + location3);
            if (location1 == 2 && location2 == 4 && location3 == 6) {
                result = true;
            } else if (location1 == 2 && location2 == 4 && location3 == 9) {
                result = true;
            } else if (location1 == 2 && location2 == 5 && location3 == 9) {
                result = true;
            } else if (location1 == 3 && location2 == 5 && location3 == 9) {
                result = true;
            }
//			System.out.println(5);
        } else if (regNo.length() == 15 && countChar(regNo, '-') == 4 && countInts(regNo) == 11) {
//			System.out.println(6);
            int location1 = regNo.indexOf('-');
            int location2 = regNo.substring(location1 + 1).indexOf('-') + location1 + 1;
            int location3 = regNo.substring(location2 + 1).indexOf('-') + location2 + 1;
            int location4 = regNo.substring(location3 + 1).indexOf('-') + location3 + 1;
//			System.out.println("location1 -> " + location1);
//			System.out.println("location2 -> " + location2);
//			System.out.println("location3 -> " + location3);
//			System.out.println("location4 -> " + location4);
            if (location1 == 2 && location2 == 4 && location3 == 6 && location4 == 10) {
                result = true;
            }
        }
        return result;
    }

    public static String createValidFormatDepartment(String department) {
        String oldDepartment = removeChar(department, ' ').toLowerCase();
        String newDepartment = "";
        String[] validDepartments = {"ee", "me", "cis"};

        if (oldDepartment.contains(validDepartments[0])) {
            newDepartment = "DEE";
        } else if (oldDepartment.contains(validDepartments[1])) {
            newDepartment = "DME";
        } else if (oldDepartment.contains(validDepartments[2])) {
            newDepartment = "DCIS";
        }

        return newDepartment;
    }

    public static boolean isValidDepartment(String department) {
        department = removeChar(department.toLowerCase(), ' ');
        String[] validDepartments = {"ee", "me", "cis"};
        boolean result = false;

        for (int i = 0; i < validDepartments.length; i++) {
            if (department.contains(validDepartments[i])) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static String createValidFormatPhoneNo(String phoneNo) {
        String oldPhoneNo = removeChar(phoneNo, ' ');
        oldPhoneNo = removeChar(oldPhoneNo, '-');
        String newPhoneNo = "";

        int count = 0;
        if (isValidPhoneNo(phoneNo)) {
            for (int i = 0; i < 15; i++) {
                if (i == 2 || i == 4 || i == 6 || i == 10) {
                    newPhoneNo += '-';
                } else {
                    newPhoneNo += oldPhoneNo.charAt(count);
                    count++;
                }
            }
        }

        return newPhoneNo;
    }

    public static boolean isValidPhoneNo(String phoneNo) {
        phoneNo = removeChar(phoneNo, ' ');
        boolean result = false;
        return result;
    }

    public static String createValidFormatGender(String gender) {
        String oldGender = removeChar(gender.toLowerCase(), ' ');
        String newGender = "";
        if (oldGender.charAt(0) == 'm') {
            newGender = "MALE";
        } else if (oldGender.charAt(0) == 'f') {
            newGender = "FEMALE";
        }
        return newGender;
    }

    public static boolean isValidGender(String gender) {
        gender = removeChar(gender.toLowerCase(), ' ');
        boolean result = false;
        if (gender.charAt(0) == 'm' || gender.charAt(0) == 'f') {
            result = true;
        }
        return result;
    }

    public static int countInts(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                count++;
            }
        }
        return count;
    }

    public static int countChar(String string, char ch) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static String removeChar(String string, char ch) {
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ch) {
                newString += string.charAt(i);
            }
        }
        return newString;
    }

}
