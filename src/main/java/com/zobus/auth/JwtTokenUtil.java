package com.zobus.auth;




public class JwtTokenUtil {
//	
//	@SuppressWarnings("deprecation")
//	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//	
//    @SuppressWarnings("deprecation")
//	public static String generateToken(String userId, String username, String role) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("sub", userId);
//        claims.put("username", username);
//        claims.put("roles", role);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(SECRET_KEY)
//                .compact();
//    }
//
//    @SuppressWarnings("deprecation")
//	public static Claims decodeToken(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
//    }
//    
//    public static void main(String[] args) {
////    	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzMiIsInJvbGVzIjoidXNlciIsInVzZXJuYW1lIjoic2FyYXZhbmFuIiwiaWF0IjoxNzAxMjUyNzIyLCJleHAiOjE3MDEzMzkxMjJ9.khtOzgywPZKtIimbhOx1eT655we9WpRT4eNrSTIprsk";
//    	String token = generateToken("32", "saravanan", "user" );
//    	System.out.println(token);
//    	System.out.println(token+"\n\n\n"+decodeToken(token));
//    	System.out.println(SECRET_KEY);
//	}
}
