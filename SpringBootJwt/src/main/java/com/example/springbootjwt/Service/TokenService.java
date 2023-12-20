package com.example.springbootjwt.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;

import java.io.UnsupportedEncodingException;
import java.util.Date;


public class TokenService {

    public static final String TOKEN_SECRET = "dfghsdDSF324dfsafsda";

    public String createTokenAndEncrypt(ObjectId userId){   //create -> withClaim -> sign
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create().
                            withClaim("userId", userId.toString()).
                            withClaim("createAt", new Date()).
                            sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException | JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String identifyTokenAndDecrypt(String token){    //verify -> decodedJwt -> getClaim
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asString();
        } catch (UnsupportedEncodingException | JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
