package vn.alaxed.booksellingweb_backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtResponse {
    private final String jwt;

}
