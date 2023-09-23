package in.ovaku.frame.framebackend.dtos.requests;
/*
 * Copyright (c) 2022 Ovaku.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class of client registration.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains client registration details")
public class ClientRegistrationDto {
    /**
     * It represents client phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Client contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
}
