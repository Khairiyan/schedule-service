package com.project.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ErrorDto {

    private String[] message;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Register{
        private String username;
        private String password;
        private String email;
    }
}
