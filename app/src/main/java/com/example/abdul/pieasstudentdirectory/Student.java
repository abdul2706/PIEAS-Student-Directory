package com.example.abdul.pieasstudentdirectory;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {

    private HashMap<String, String> studentData = new HashMap<>();
    public static final String[] STUDENT_KEYS = {"studentName", "fatherName", "regNo", "batch", "department", "semester",
            "roomNo", "hostel", "cgpa", "age", "gender", "bloodGroup", "phoneNo", "email", "address"};
    private Photo photo = new Photo();

//    public Student() {
//        this("Abdul Rehman Khan", "Tanveer Ahmed Khan", "03310032017", "17-21", "dcis",
//                "3", "204", "a", "3.91", "19", "m", "A+", "03311205526",
//                "bscs1725@pieas.edu.pk", "gujranwala");
//    }

    Student(String[] values){
        this(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7],
                values[8], values[9], values[10], values[11], values[12], values[13], values[14]);
    }

    private Student(String studentName, String fatherName, String regNo, String batch, String department, String semester, String roomNo,
                    String hostel, String cgpa, String age, String gender, String bloodGroup, String phoneNo, String email, String address) {
        setStudentData(STUDENT_KEYS[0], studentName);
        setStudentData(STUDENT_KEYS[1], fatherName);
        setStudentData(STUDENT_KEYS[2], regNo);
        setStudentData(STUDENT_KEYS[3], batch);
        setStudentData(STUDENT_KEYS[4], department);
        setStudentData(STUDENT_KEYS[5], semester);
        setStudentData(STUDENT_KEYS[6], roomNo);
        setStudentData(STUDENT_KEYS[7], hostel);
        setStudentData(STUDENT_KEYS[8], cgpa);
        setStudentData(STUDENT_KEYS[9], age);
        setStudentData(STUDENT_KEYS[10], gender);
        setStudentData(STUDENT_KEYS[11], bloodGroup);
        setStudentData(STUDENT_KEYS[12], phoneNo);
        setStudentData(STUDENT_KEYS[13], email);
        setStudentData(STUDENT_KEYS[14], address);
    }

    public String getStudentData(String key) {
        return studentData.get(key);
    }

    public void setStudentData(String key, String value) {
        switch (key){
            case "regNo":
                studentData.put(key, getValidRegNo(value));
                break;
            case "department":
                studentData.put(key, getValidDepartment(value));
                break;
            case "semester":
                studentData.put(key, getValidSemester(value));
                break;
            case "hostel":
                studentData.put(key, getValidHostel(value));
                break;
            case "cgpa":
                studentData.put(key, getValidCGPA(value));
                break;
            case "gender":
                studentData.put(key, getValidGender(value));
                break;
            case "phoneNo":
                studentData.put(key, getValidPhoneNo(value));
                break;
            default:
                studentData.put(key, value);
        }
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : STUDENT_KEYS) {
            stringBuilder.append(key).append(" -> ").append(studentData.get(key)).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object std) {
        if (std instanceof Student) {
            Student student = (Student) std;
            return this.studentData.get("regNo").equalsIgnoreCase(student.studentData.get("regNo")) ||
                    this.studentData.get("phoneNo").equalsIgnoreCase(student.studentData.get("phoneNo")) ||
                    this.studentData.get("email").equalsIgnoreCase(student.studentData.get("email"));
        }
        return false;
    }

    // Static Methods
    public static String parseStudentToString(Student std) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        for (String key : STUDENT_KEYS) {
            if(!key.equals("address")) {
                stringBuilder.append(std.studentData.get(key)).append("', '");
            } else {
                stringBuilder.append(std.studentData.get(key)).append("'");
            }
        }
        return stringBuilder.toString();
    }

    private static String getValidHostel(String hostel) {
        return isValidHostel(hostel.toUpperCase()) ? hostel : "invalid";
    }

    public static boolean isValidHostel(String hostel) {
        return hostel.contains("A") || hostel.contains("B") || hostel.contains("E") || hostel.contains("C") || hostel.contains("G") || hostel.contains("H") || hostel.contains("J");
    }

    private static String getValidPhoneNo(String phoneNo) {
        return isValidPhoneNo(phoneNo) ? phoneNo.substring(0, 4) + "-" + phoneNo.substring(4) : phoneNo;
    }

    public static boolean isValidPhoneNo(String phoneNo) {
        return phoneNo.length() == 11 && !phoneNo.contains("-");
    }

    private static String getValidSemester(String semester) {
        return isValidSemester(semester) ? semester : "invalid";
    }

    public static boolean isValidSemester(String semester) {
        return semester.compareTo("0") > 0 && semester.compareTo("9") < 0;
    }

    private static String getValidDepartment(String department) {
        department = department.toLowerCase();
        if (department.contains("ee")) {
            return "DEE";
        } else if (department.contains("me")) {
            return "DME";
        } else if (department.contains("cis") || department.contains("cs")) {
            return "DCIS";
        } else {
            return "invalid";
        }
    }

    public static boolean isValidDepartment(String department) {
        return department.contains("ee") || department.contains("me") || department.contains("cis") || department.contains("cs");
    }

    private static String getValidRegNo(String regNo) {
        return isValidRegNo(regNo) ? regNo.substring(0, 2) + "-" + regNo.substring(2, 3) + "-" + regNo.substring(3, 4) + "-" + regNo.substring(4, 7) + "-" + regNo.substring(7) : regNo;
    }

    public static boolean isValidRegNo(String regNo) {
        return regNo.length() == 11 && !regNo.contains("-");
    }

    private static String getValidGender(String gender) {
        if (gender.charAt(0) == 'm') {
            return "MALE";
        } else if (gender.charAt(0) == 'f') {
            return "FEMALE";
        } else {
            return "empty";
        }
    }

    public static boolean isValidGender(String gender) {
        return gender.toLowerCase().charAt(0) == 'm' || gender.toLowerCase().charAt(0) == 'f';
    }

    private static String getValidCGPA(String cgpa) {
        return isValidCGPA(cgpa) ? cgpa : "invalid";
    }

    public static boolean isValidCGPA(String cgpa) {
        return cgpa.compareTo("0.00") >= 0 && cgpa.compareTo("4.00") <= 0;
    }

}
