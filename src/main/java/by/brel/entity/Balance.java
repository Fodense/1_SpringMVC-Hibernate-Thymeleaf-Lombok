package by.brel.entity;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "balances")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_balance")
    private long idBalance;

    @Column(name = "balance")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_tariff")
    private Tariff tariff;
}
