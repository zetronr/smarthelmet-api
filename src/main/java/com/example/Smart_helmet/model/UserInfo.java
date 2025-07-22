package com.example.Smart_helmet.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="smartHelmetUser")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private LocalDate dateofbirth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String uuid;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private MedicInfo medicalinfo;

    public UserInfo() { }

    public Long getId(){
        return user_id;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getFullname(){
        return fullname;
    }
    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public LocalDate getDateofbirth(){
        return dateofbirth;
    }
    public void setDateofbirth(LocalDate dateofbirth){
        this.dateofbirth = dateofbirth;
    }

    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
            this.gender = gender;
    }


    public String getPassword() {
        return password; }
    public void setPassword(String password) {
        this.password = password; }

    public String getUuid(){
        return uuid;
    }
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public MedicInfo getMedicalInfo() {
        return medicalinfo;
    }

    public void setMedicalProfile(MedicInfo medicalinfo) {
        this.medicalinfo = medicalinfo;
    }

}
