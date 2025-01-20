package co.com.ecommerce.rest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProductPriceRequest {

    @NotNull(message = "Product ID cannot be null.")
    private Long productId;

    @NotNull(message = "Brand ID cannot be null.")
    private Long brandId;

    @NotBlank(message = "Application Date cannot be blank.")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",
            message = "Application Date must be in the format 'YYYY-MM-DDTHH:mm:ss'."
    )
    private String applicationDate;

}
