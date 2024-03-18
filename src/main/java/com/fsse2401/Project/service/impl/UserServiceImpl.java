package com.fsse2401.Project.service.impl;

import com.fsse2401.Project.data.User.Entity.UserEntity;
import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import com.fsse2401.Project.repository.UserRepository;
import com.fsse2401.Project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
//        Optional<UserEntity> userEntityOptional = userRepository.findByFirebase_uid(firebaseUserData.getFireBaseUid());
//        if(userEntityOptional.isEmpty()){
//            UserEntity userEntity = new UserEntity(firebaseUserData);
//            return userRepository.save(userEntity);
//        } else {
//            return userEntityOptional.get();

            return userRepository.findByFirebaseUid(firebaseUserData.getFireBaseUid()).orElseGet(() ->userRepository.save(new UserEntity(firebaseUserData)));
        }


}
