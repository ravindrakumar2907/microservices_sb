package com.product.dto;

import java.util.List;

/**
 * DTO records for products payload.
 * Top-level Products record contains paging info and list of Product records.
 */
public record Products(List<Product> products, int total, int skip, int limit) {

    public static record Product(
            int id,
            String title,
            String description,
            String category,
            double price,
            double discountPercentage,
            double rating,
            int stock,
            List<String> tags,
            String brand,
            String sku,
            double weight,
            Dimensions dimensions,
            String warrantyInformation,
            String shippingInformation,
            String availabilityStatus,
            List<Review> reviews,
            String returnPolicy,
            int minimumOrderQuantity,
            Meta meta,
            String thumbnail,
            List<String> images
    ) {}

    public static record Dimensions(double width, double height, double depth) {}

    public static record Review(int rating, String comment, String date, String reviewerName, String reviewerEmail) {}

    public static record Meta(String createdAt, String updatedAt, String barcode, String qrCode) {}
}
