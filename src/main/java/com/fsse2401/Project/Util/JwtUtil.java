package com.fsse2401.Project.Util;

import com.fsse2401.Project.data.User.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUserData (JwtAuthenticationToken jwtToekn){

        return new FirebaseUserData(jwtToekn);
    }
}
