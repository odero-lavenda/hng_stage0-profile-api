package com.hng.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {

        @JsonProperty("status")
        private String status;

        @JsonProperty("user")
        private UserInfo user;

        @JsonProperty("timestamp")
        private String timestamp;

        @JsonProperty("fact")
        private String fact;

}
