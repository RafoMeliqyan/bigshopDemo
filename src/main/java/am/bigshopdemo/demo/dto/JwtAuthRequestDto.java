package am.bigshopdemo.demo.dto;

import lombok.Data;

@Data
public class JwtAuthRequestDto {

    private String email;
    private String password;

}
