package com.emysilva.pathologyservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologyList {
    private List<Pathology> pathologyList;
}
