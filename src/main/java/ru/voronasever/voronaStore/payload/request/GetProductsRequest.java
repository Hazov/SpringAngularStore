package ru.voronasever.voronaStore.payload.request;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;

@Getter
public class GetProductsRequest {
    private String category;
    private int currentPage;
    private int productsOnPage;
}
