package com.example.homeservice1.request;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HomeRequest {

    @NotBlank
    private String name;
    @Nullable
    private String address;
}
