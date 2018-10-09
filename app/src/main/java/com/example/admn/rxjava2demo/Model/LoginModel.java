package com.example.admn.rxjava2demo.Model;

public class LoginModel {
    private boolean status;
    private Data data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginModel(ApiLoginModel apiUser) {
        this.status = apiUser.status;
        this.data = apiUser.data;
        this.message = apiUser.message;
    }

    public class Data {
        private int userId;
        private String email;
        private String userName;
        private String firstname;
        private String lastname;
        private String displayname;
        private String gender;
        private String userType;
        private String zipcode;
        private int hraId;
        private int employerId;
        private String userimageurl;
        private String birthdate;
        private String phone;
        private String isEmailVerified;
        private String isHraQuestion1;
        private String isHraQuestion2;
        private Object roleId;
        private boolean isHavingIMSaccess;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getDisplayname() {
            return displayname;
        }

        public void setDisplayname(String displayname) {
            this.displayname = displayname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public int getHraId() {
            return hraId;
        }

        public void setHraId(int hraId) {
            this.hraId = hraId;
        }

        public int getEmployerId() {
            return employerId;
        }

        public void setEmployerId(int employerId) {
            this.employerId = employerId;
        }

        public String getUserimageurl() {
            return userimageurl;
        }

        public void setUserimageurl(String userimageurl) {
            this.userimageurl = userimageurl;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(String isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getIsHraQuestion1() {
            return isHraQuestion1;
        }

        public void setIsHraQuestion1(String isHraQuestion1) {
            this.isHraQuestion1 = isHraQuestion1;
        }

        public String getIsHraQuestion2() {
            return isHraQuestion2;
        }

        public void setIsHraQuestion2(String isHraQuestion2) {
            this.isHraQuestion2 = isHraQuestion2;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public boolean isIsHavingIMSaccess() {
            return isHavingIMSaccess;
        }

        public void setIsHavingIMSaccess(boolean isHavingIMSaccess) {
            this.isHavingIMSaccess = isHavingIMSaccess;
        }

    }

}
