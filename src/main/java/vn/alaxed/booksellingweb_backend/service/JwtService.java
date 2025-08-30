package vn.alaxed.booksellingweb_backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.alaxed.booksellingweb_backend.dao.RoleRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import vn.alaxed.booksellingweb_backend.entity.Role;
import vn.alaxed.booksellingweb_backend.entity.User;

@Component
public class JwtService {

    private final RoleRepository roleRepository;
    public static final String SECRET  = "150820032007200QYTTYQTVLTTQTTQVTLTT20072003036SDT871TVQ6800QZMLPOIUWERTGBHUYFJXM0087613320055";

    @Autowired
    UserService userService;

    JwtService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Tạo JWT dựa trên username
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        User user = userService.findByUsername(username);
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;
        if(user != null && user.getListRoles().size() > 0 ){
            List<Role> list = user.getListRoles();
            for(Role r: list){
                if (r.getTitle().equals("Admin")) {
                    isAdmin = true;
                }
                if (r.getTitle().equals("Staff")) {
                    isStaff = true;
                    
                }if (r.getTitle().equals("User")) {
                    isUser = true;
                    
                }
            }

        }
        claims.put("id", user.getUserId());
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        return createToken(claims, username);
    }

    // Tạo JWT với các claims đã chọn
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(getSigneKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Lấy serect key
    private Key getSigneKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Trích xuất thông tin
    private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(getSigneKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    //Trich xuất thông tin cho một claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //Kiểm tra thời gian hết hạn từ JWT
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //Kiểm tra thời gian hết hạn từ JWT
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    

    //Kiểm tra cái JWT đã hết hạn
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Kiểm tra tính hợp lệ
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))&&!isTokenExpired(token);
    }
} 

