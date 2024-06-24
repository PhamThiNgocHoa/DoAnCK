package com.example.myapplication.network.dto.response;
import java.io.Serializable;

    public class CustomerResponseDTO implements Serializable {

        private int id;

        private String fullname;

        private String username;

        private String email;

        private String phone;
        private String password;

        public CustomerResponseDTO(int id, String fullname, String username, String email, String phone, String password) {
            this.id = id;
            this.fullname = fullname;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
