package com.upc.jara.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientCineDTO {
    private Long jlId;
    private Long jlDni;
    private String jlName;
    private  int jlDependets;
    private int jlStock;
    private double jlCuotaNeta;
}
