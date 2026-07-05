package com.jobSearch.dto.request;

import com.jobSearch.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateApplicationStatusRequest {

    @NonNull
    private ApplicationStatus status;

}
