package am.bigshopdemo.demo.dto;

import am.bigshopdemo.demo.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponseDto {

    private String token;
    private User user;

}
