package com.emysilva.pathologyservice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pathology {
    private Integer id;
    private String disease;
}
