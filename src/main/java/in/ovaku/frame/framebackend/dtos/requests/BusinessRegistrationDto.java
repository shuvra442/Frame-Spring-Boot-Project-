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
 * This class is a dto class of business registration.
 *
 * @author Sohan
 * @version 1.0
 * @since 24/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains business registration details")
public class BusinessRegistrationDto {
    /**
     * It represents business organization phone number.
     * TODO: 26/06/22 Add SMS API validator
     */
    @ApiModelProperty(name = "phoneNo", notes = "Business contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
}
