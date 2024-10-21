### JWT (JSON Web Token) Complete Notes with Diagram

---

### 1. **What is JWT?**

JWT (JSON Web Token) is an open standard (RFC 7519) that defines a compact, self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed using a secret or public/private key pair.

**Key Characteristics of JWT**:
- **Compact**: JWTs are compact and can be sent via URL, POST parameters, or HTTP headers, and are small enough to be sent in URLs.
- **Self-contained**: JWT contains all the information needed about the user, eliminating the need to query a database multiple times.
- **Stateless**: Once a JWT is created and issued to the client, the server does not need to store session data.

---

### 2. **Structure of JWT**

A JWT consists of three parts separated by dots (`.`):
1. **Header**
2. **Payload**
3. **Signature**

```plaintext
Header.Payload.Signature
```

#### **Header**
The header typically consists of two parts: the type of the token (i.e., JWT) and the signing algorithm being used (e.g., HMAC SHA256 or RSA).

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

This is then Base64 encoded.

#### **Payload**
The payload contains the claims. Claims are statements about an entity (typically, the user) and additional data. There are three types of claims:
- **Registered claims**: Predefined claims like `iss` (issuer), `exp` (expiration time), `sub` (subject), `aud` (audience).
- **Public claims**: Custom claims agreed upon by parties.
- **Private claims**: Claims that are defined by users for their applications.

Example payload:
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true,
  "iat": 1516239022
}
```

This payload is Base64 encoded.

#### **Signature**
To create the signature, you have to take the encoded header, the encoded payload, a secret, and the algorithm specified in the header, and sign it.

For example, if you are using HMAC SHA256, the signature will be created as:

```plaintext
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  secret
)
```

The signature ensures that the token hasn’t been altered. If the signature matches, the server can trust the token's integrity.

---

### 3. **How JWT Works?**

Here’s a step-by-step process showing how JWT works with a diagram for better understanding:

#### **Step 1: User Logs In**
- The user provides credentials (username and password) to the authentication server (e.g., an API or backend service).
- The server verifies the credentials.

#### **Step 2: Server Generates JWT**
- Once the user is authenticated, the server creates a JWT that contains the user details (e.g., username, roles).
- The server signs the JWT using a secret key or public/private key pair.

#### **Step 3: JWT is Sent to the Client**
- The server sends the signed JWT back to the client (usually in the response body or as a cookie).
- The client stores the JWT, typically in local storage or session storage.

#### **Step 4: Client Sends JWT on Each Request**
- For subsequent requests, the client includes the JWT in the `Authorization` header as a **Bearer** token.

```plaintext
Authorization: Bearer <JWT>
```

#### **Step 5: Server Verifies JWT**
- On receiving the request, the server extracts the JWT from the header and verifies the signature using the secret or public key.
- If the token is valid, the server processes the request; otherwise, it rejects the request.

#### **Step 6: Access Granted/Denied**
- If the JWT is valid, the user is granted access to the requested resource.
- If the JWT is expired or invalid, the server denies access.

---

### 4. **JWT Workflow Diagram**

Here's a simplified diagram showing how JWT is used for authentication and authorization:

```
  +--------+            +---------------+          +-------------+
  | Client |            | Authentication |          | Resource    |
  |        |            | Server (API)   |          | Server (API)|
  +--------+            +---------------+          +-------------+
       |                       |                         |
       | 1. Send credentials    |                         |
       +----------------------->|                         |
       |                        |                         |
       |      2. Authenticate    |                         |
       |<------------------------+                         |
       |                        |                         |
       | 3. Return JWT Token     |                         |
       +<------------------------+                         |
       |                        |                         |
       | 4. Store JWT in Local   |                         |
       |    Storage/Cookie       |                         |
       |                        |                         |
       | 5. Send JWT in Header   |                         |
       +----------------------->|                         |
       |  Authorization: Bearer  |                         |
       |        <JWT>            |                         |
       |                        |                         |
       | 6. Verify JWT           |                         |
       |<------------------------+                         |
       |                        |                         |
       |   7. Access Granted     |                         |
       +-----------------------> |                         |
       |                        |                         |
```

---

### 5. **JWT Claims**

#### Common Claims
- **iss**: Issuer of the JWT.
- **sub**: Subject (usually the user ID).
- **aud**: Audience (the recipient for whom the token is intended).
- **exp**: Expiration time (when the token will expire).
- **iat**: Issued at (when the token was issued).
- **nbf**: Not before (token is not valid before this time).

You can also add custom claims (e.g., user roles, permissions) as needed.

---

### 6. **Advantages of JWT**

1. **Stateless**: No need for a session to be stored on the server. JWT itself contains all the necessary data.
2. **Compact**: Suitable for mobile devices or web apps, as JWTs are compact in size.
3. **Self-contained**: JWT contains the information of the user (claims), so there’s no need to query a database every time.
4. **Decentralized Authentication**: JWT allows authentication across different domains or services.

---

### 7. **Disadvantages of JWT**

1. **Revocation**: Once a JWT is issued, it cannot be revoked. If a user logs out or the token is compromised, it’s difficult to invalidate it before expiration.
2. **Token Size**: Since all the user information is contained in the token, it can become large if many claims are included.
3. **Expiration**: Tokens should be short-lived to prevent potential misuse. But this can cause frequent re-authentication.

---

### 8. **JWT Expiration and Refresh Tokens**

- JWTs should have a short expiration time (`exp` claim) for security.
- **Refresh Tokens** are used to issue a new JWT when the original JWT expires. They are stored securely and can be used to obtain a new access token without requiring the user to log in again.

---

### 9. **JWT Security Best Practices**

- **Use HTTPS**: Always use HTTPS to protect the token from being intercepted.
- **Sign Tokens**: Always sign JWTs with a strong encryption algorithm like RS256.
- **Set Expiration**: Always set a short expiration time (`exp` claim) to limit the lifetime of a token.
- **Use Refresh Tokens**: For long-lived sessions, use refresh tokens to regenerate JWTs without requiring the user to log in.
- **Secure Storage**: Store JWTs securely in `HttpOnly` cookies to prevent cross-site scripting (XSS) attacks.

---

### 10. **Spring Boot JWT Example**

Here’s a simplified example of how to implement JWT authentication in Spring Boot.

#### Dependencies
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

#### JWT Utility Class
```java
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private String secret = "secret";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
```
 
---

### 11. **Conclusion**

JWT is widely used for stateless authentication in modern web and mobile applications. It ensures

security, scalability, and a decentralized approach to handling authentication. However, it’s important to handle token expiration, revocation, and secure storage to mitigate security risks.