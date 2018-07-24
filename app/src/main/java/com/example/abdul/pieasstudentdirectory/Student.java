package com.example.abdul.pieasstudentdirectory;

public class Student {

    private String studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester, batch, department, email, regNo, gender;

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
        this.hostel = hostel.toUpperCase();
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
        this.gender = getValidGender(gender);
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

    public static String getValidRegNo(String regNo) {
        String newRegNo = "empty";
        if (regNo.length() == 11) {
            newRegNo = regNo.substring(0, 2) + "-" + regNo.substring(2, 3) + "-" + regNo.substring(3, 4) + "-" + regNo.substring(4, 7) + "-" + regNo.substring(7);
        }
        return newRegNo;
    }

    public static String getValidDepartment(String department) {
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

    public static String getValidPhoneNo(String phoneNo) {
        String newPhoneNo = "empty";
        if (phoneNo.length() == 11) {
            newPhoneNo = phoneNo.substring(0, 4) + "-" + phoneNo.substring(4);
        }
        return newPhoneNo;
    }

    public static String getValidGender(String gender) {
        String newGender = "empty";
        if (gender.charAt(0) == 'm') {
            newGender = "MALE";
        } else if (gender.charAt(0) == 'f') {
            newGender = "FEMALE";
        }
        return newGender;
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
