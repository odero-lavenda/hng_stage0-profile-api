package com.hng;

import com.hng.controller.ProfileController;
import com.hng.dto.ProfileResponseDto;
import com.hng.dto.UserInfo;
import com.hng.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfileService profileService;

    @Test
    void testGetProfile_Success() throws Exception {
        // Arrange
        UserInfo userInfo = new UserInfo(
                "test@example.com",
                "Test User",
                "Java/Spring Boot"
        );

        ProfileResponseDto mockResponse = new ProfileResponseDto(
                "success",
                userInfo,
                "2025-10-18T12:34:56.789Z",
                "Cats can rotate their ears 180 degrees."
        );

        when(profileService.getProfileWithCatFact()).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/me"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.user.email").value("test@example.com"))
                .andExpect(jsonPath("$.user.name").value("Test User"))
                .andExpect(jsonPath("$.user.stack").value("Java/Spring Boot"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.fact").exists());
    }

    @Test
    void testGetProfile_ServiceFailure() throws Exception {
        // Arrange
        when(profileService.getProfileWithCatFact())
                .thenThrow(new RuntimeException("External API unavailable"));

        // Act & Assert
        mockMvc.perform(get("/me"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testHealthCheck() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("healthy"))
                .andExpect(jsonPath("$.timestamp").exists());
    }




}
