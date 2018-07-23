package com.example.abdul.pieasstudentdirectory;

import java.util.*;
import java.io.Serializable;

public class Student implements Serializable {

    private String studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester,
            batch, department, email, regNo, gender;

    public Student() {
        this("Abdul Rehman Khan", "Tanveer Ahmed Khan", "204", "A",
                "khaqan St#1, Arif Colony, Gill Road, GRW", "A+", "03311205526",
                "3", "17-21", "dcis", "abdulrehmankhan27061998@gmail.com",
                "03310032017", "Male");
    }

    public Student(Student std) {
        this(std.studentName, std.fatherName, std.roomNo, std.hostel, std.address, std.bloodGroup,
                std.phoneNo, std.semester, std.batch, std.department, std.email, std.regNo, std.gender);
    }

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
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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
        this.hostel = hostel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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
        this.department = department;
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
        this.regNo = regNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
                "gender -> " + gender + "\n";
    }

    @Override
    public boolean equals(Object std) {
        //studentName, fatherName, roomNo, hostel, address, bloodGroup,
        //phoneNo, semester, batch, department, email, regNo, gender;
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

    // Static Methods
    public static String parseStudentToString(Student std) {
        return std.studentName + ',' + std.fatherName + ',' + std.roomNo + ',' + std.hostel + ',' +
                std.address + ',' + std.bloodGroup + ',' + std.phoneNo + ',' + std.semester + ',' +
                std.batch + ',' + std.department + ',' + std.email + ',' + std.regNo + ',' + std.gender;
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

        return new Student(studentName, fatherName, roomNo, hostel, address, bloodGroup,
                phoneNo, semester, batch, department, email, regNo, gender);
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
