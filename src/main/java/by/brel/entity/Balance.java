package by.brel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "balances")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@ApiModel(description = "Balance entity.")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_balance")
    @ApiModelProperty(value = "${balances.model.idBalance}", example = "1", required = true, position = 0)
    private long idBalance;

    @Column(name = "balance")
    @ApiModelProperty(value = "${balances.model.balance}", example = "1000", position = 3)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    @ApiModelProperty(value = "${balances.model.customer}", example = "1 - ID Customer", position = 1)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_tariff")
    @ApiModelProperty(value = "${balances.model.tariff}", example = "1 - ID Tariff", position = 2)
    private Tariff tariff;
}
