package com.example.abdul.pieasstudentdirectory;

import java.io.Serializable;

public class Student implements Serializable {

    private String studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester, batch, department, email, regNo, gender;

//    public Student() {
//        this("Abdul Rehman Khan", "Tanveer Ahmed Khan", "204", "A",
//                "khaqan St#1, Arif Colony, Gill Road, GRW", "A+", "03311205526",
//                "3", "17-21", "dcis", "abdulrehmankhan27061998@gmail.com",
//                "03310032017", "Male");
//    }
//
//    public Student(Student std) {
//        this(std.studentName, std.fatherName, std.roomNo, std.hostel, std.address, std.bloodGroup,
//                std.phoneNo, std.semester, std.batch, std.department, std.email, std.regNo, std.gender);
//    }

    public Student(String studentName, String fatherName, String roomNo, String hostel, String address,
                   String bloodGroup, String phoneNo, String semester, String batch, String department,
                   String email, String regNo, String gender) {
        setStudentName(studentName);
        setFatherName(fatherName);
        setRoomNo(roomNo);
        setHostel(hostel);
        setAddress(address);
        setBloodGroup(bloodGroup);
        setPhoneNo(phoneNo);
        setSemester(semester);
        setBatch(batch);
        setDepartment(department);
        setEmail(email);
        setRegNo(regNo);
        setGender(gender);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName.toUpperCase();
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName.toUpperCase();
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = getValidHostel(hostel.toUpperCase());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.toUpperCase();
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup.toUpperCase();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = getValidPhoneNo(phoneNo);
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = getValidSemester(semester);
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = getValidDepartment(department);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = getValidRegNo(regNo);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = getValidGender(gender.toLowerCase());
    }

    @Override
    public String toString() {
        //studentName, fatherName, roomNo, hostel, address, bloodGroup,
        //phoneNo, semester, batch, department, email, regNo, gender;
        return "studentName -> " + studentName + "\n" +
                "fatherName -> " + fatherName + "\n" +
                "roomNo -> " + roomNo + "\n" +
                "hostel -> " + hostel + "\n" +
                "address -> " + address + "\n" +
                "bloodGroup -> " + bloodGroup + "\n" +
                "phoneNo -> " + phoneNo + "\n" +
                "semester -> " + semester + "\n" +
                "batch -> " + batch + "\n" +
                "department -> " + department + "\n" +
                "email -> " + email + "\n" +
                "regNo -> " + regNo + "\n" +
                "gender -> " + gender;
    }

    @Override
    public boolean equals(Object std) {
        //studentName, fatherName, roomNo, hostel, address, bloodGroup,
        //phoneNo, semester, batch, department, email, regNo, gender;
        if(std instanceof Student) {
            Student student = (Student) std;
            return this.studentName.equals(student.getStudentName()) &&
                    this.fatherName.equals(student.getFatherName()) &&
                    this.roomNo.equals(student.getRoomNo()) &&
                    this.hostel.equals(student.getHostel()) &&
                    this.address.equals(student.getAddress()) &&
                    this.bloodGroup.equals(student.getBloodGroup()) &&
                    this.phoneNo.equals(student.getPhoneNo()) &&
                    this.semester.equals(student.getSemester()) &&
                    this.batch.equals(student.getBatch()) &&
                    this.department.equals(student.getDepartment()) &&
                    this.email.equals(student.getEmail()) &&
                    this.regNo.equals(student.getRegNo()) &&
                    this.gender.equals(student.getGender());
        }
        return false;
    }

    // Static Methods
    public static String parseStudentToString(Student std) {
        return "'" + std.studentName + "', '" + std.fatherName + "', '" + std.roomNo + "', '" + std.hostel + "', '" +
                std.address + "', '" + std.bloodGroup + "', '" + std.phoneNo + "', '" + std.semester + "', '" + std.batch + "', '" +
                std.department + "', '" + std.email + "', '" + std.regNo + "', '" + std.gender + "'";
    }

    public static Student parseStringToStudent(String line) {
        String studentName = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String fatherName = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String roomNo = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String hostel = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String address = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String bloodGroup = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String phoneNo = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String semester = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String batch = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String department = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String email = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String regNo = line.substring(0, line.indexOf(";"));
        line = line.substring(line.indexOf(";") + 1);

        String gender = line.substring(0, line.indexOf(";"));

        return new Student(studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester, batch, department, email, regNo, gender);
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
            return  "DME";
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

}
