package bank.eltropy;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Value
@Builder
@JsonDeserialize(builder = TransferRequest.TransferRequestBuilder.class)
public class TransferRequest {

    @Min(1)
    @JsonProperty("ownerId")
    private Long ownerId;

    @Min(1)
    @JsonProperty("ownerAccountId")
    private Long ownerAccountId;

    @NotNull
    @NotEmpty
    @JsonProperty("clientAccountNumber")
    private String PrimaryclientAccountNumber;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @JsonProperty("totalPrice")
    private BigDecimal amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TransferRequestBuilder {
    }
}