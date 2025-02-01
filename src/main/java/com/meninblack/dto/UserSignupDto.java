package com.meninblack.dto;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserSignupDto {

    @NonNull
    private String email;

    @NonNull
    private String password;

    @Indexed(unique = true)
    @NonNull
    private Long phone_number;

    @NonNull
    private String name;
}
