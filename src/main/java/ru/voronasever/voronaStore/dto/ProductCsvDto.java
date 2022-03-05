package ru.voronasever.voronaStore.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCsvDto {
    private String article;
    private String name;
    private String description;
    private int price;
    private short category;
    private int countOnStock;
}
