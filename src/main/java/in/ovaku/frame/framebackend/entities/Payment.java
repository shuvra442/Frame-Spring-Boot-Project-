package in.ovaku.frame.framebackend.entities;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.enums.PaymentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity class with 9 member variables.
 * It defines payments information.
 *
 * @author Sohan
 * @version 1.0
 * @since 22/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel(description = "It contains Payment details")
public class Payment {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Payment Id", required = true, value = "1")
    private Long id;
    /**
     * It has one-to-many relation with {@link Status} class.
     * It represents payment status.
     */
    @ManyToOne
    @JoinColumn(name = "status_id")
    @ApiModelProperty(name = "status", notes = "Payment status")
    private Status status;
    /**
     * It represents payment response.
     */
    @Lob
    @ApiModelProperty(name = "response", notes = "Payment response", required = true, value = "xyz")
    private String response;
    /**
     * It represents payment date.
     */
    @ApiModelProperty(name = "date", notes = "Payment date", required = true, value = "00/00/0000")
    private Date date;
    /**
     * It represents payment amount.
     */
    @Column(precision = 13, scale = 4)
    @ApiModelProperty(name = "amount", notes = "Payment amount", required = true, value = "0,000")
    private BigDecimal amount;
    /**
     * It represents payment type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @ApiModelProperty(name = "paymentType", notes = "Payment type", required = true, value = "xyz")
    private PaymentType paymentType;
    /**
     * It has one-to-many relation with {@link Subscription} class.
     * It represents payments for subscription pack.
     */
    @OneToMany(mappedBy = "payment")
    @ApiModelProperty(name = "subscriptions", notes = "Payment for subscriptions")
    private Set<Subscription> subscriptions;
    /**
     * It represents record created date.
     */
    @ApiModelProperty(name = "createdDate", notes = "Payment created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record Updated date.
     */
    @ApiModelProperty(name = "updatedDate", notes = "Payment updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}

