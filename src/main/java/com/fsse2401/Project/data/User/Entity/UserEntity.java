package com.fsse2401.Project.data.User.Entity;

import com.fsse2401.Project.data.CartItem.Entity.CartItemEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "firebase_uid", nullable = false)
    private String firebaseUid;

    @OneToMany(mappedBy = "user")
    List<CartItemEntity> userHasInCarts;

    public UserEntity() {
    }

    public UserEntity(FirebaseUserData firebaseUserData) {
        this.firebaseUid = firebaseUserData.getFireBaseUid();
        this.email = firebaseUserData.getEmail();
    }

    @Column(nullable = false)
    private String email;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebase_uid() {
        return firebaseUid;
    }

    public void setFirebase_uid(String firebase_uid) {
        this.firebaseUid = firebase_uid;
    }
}
