package by.brel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariffs")
@Data
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tariff")
    private long idTariff;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    private List<Balance> balances;
}
