package by.brel.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "balances")
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_balance")
    private long idBalance;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tariff")
    private Tariff tariff;
}
