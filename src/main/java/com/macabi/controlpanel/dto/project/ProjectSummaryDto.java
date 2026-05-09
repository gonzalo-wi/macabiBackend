package com.macabi.controlpanel.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSummaryDto {
    
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private String userName;
}
