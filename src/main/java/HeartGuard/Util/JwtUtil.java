package HeartGuard.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private Key secretKey = Keys.secretKeyFor( SignatureAlgorithm.HS256 );

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // [1] 회원 토큰 생성
    public String createToken1( String uid ){
        String token1 = Jwts.builder()
                .setSubject( uid )
                .setIssuedAt( new Date() )
                .setExpiration( new Date( System.currentTimeMillis() + ( 1000 * 60 * 60 * 24 ) ) )
                .signWith( secretKey )
                .compact();
        stringRedisTemplate.opsForValue().set("JWT:"+uid , token1 , 24 , TimeUnit.HOURS );
        System.out.println( stringRedisTemplate.keys("*") );
        System.out.println( stringRedisTemplate.opsForValue().get("JWT:"+uid) );
        return token1;

    }

    // [2] 회원 토큰 검증
    public String validateToken1( String token1 ){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey( secretKey )
                    .build()
                    .parseClaimsJws( token1 )
                    .getBody();
            System.out.println( claims.getSubject() );
            String uid = claims.getSubject();
            String redisToken = stringRedisTemplate.opsForValue().get("JWT:"+uid);
            if( token1.equals( redisToken ) ){ return  uid; }
            else{  System.out.println(" >> 중복 로그인 감지 또는 토큰 없음");  }
        }catch ( ExpiredJwtException e){
            System.out.println(" >> JWT 토큰 기한 만료 : " + e );
        }catch ( JwtException e ){
            System.out.println(" >> JWT 예외 : " + e );
        }
        return null;
    }

    // [3] 로그아웃 시 회원 토큰 삭제
    public void deleteToken1( String uid ){
        stringRedisTemplate.delete( "JWT:"+uid );
    }

    // [4] 병원 토큰 생성
    public String createToken2( String hid ){
        String token2 = Jwts.builder()
                .setSubject( hid )
                .setIssuedAt( new Date() )
                .setExpiration( new Date( System.currentTimeMillis() + ( 1000 * 60 * 60 * 24 ) ) )
                .signWith( secretKey )
                .compact();
        stringRedisTemplate.opsForValue().set("JWT:"+hid , token2 , 24 , TimeUnit.HOURS );
        System.out.println( stringRedisTemplate.keys("*") );
        System.out.println( stringRedisTemplate.opsForValue().get("JWT:"+hid) );
        return token2;
    }

    // [5] 병원 토큰 검증
    public String validateToken2( String token2 ){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey( secretKey )
                    .build()
                    .parseClaimsJws( token2 )
                    .getBody();
            System.out.println( claims.getSubject() );
            String hid = claims.getSubject();
            String redisToken = stringRedisTemplate.opsForValue().get("JWT:"+hid);
            System.out.println("redisToken = " + redisToken );
            if( token2.equals( redisToken ) ){ return  hid; }
            else{  System.out.println(" >> 중복 로그인 감지 또는 토큰 없음");  }

        }catch ( ExpiredJwtException e){
            System.out.println(" >> JWT 토큰 기한 만료 : " + e );
        }catch ( JwtException e ){
            System.out.println(" >> JWT 예외 : " + e );
        }
        return null;
    }

    // [3] 로그아웃 시 병원 토큰 삭제
    public void deleteToken2( String hid ){
        stringRedisTemplate.delete( "JWT:"+hid );
    }
}
