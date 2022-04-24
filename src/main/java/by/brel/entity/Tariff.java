package by.brel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariffs")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    private List<Balance> balances;

    @Override
    public String toString() {
        return "Tariff{" +
                "idTariff=" + idTariff +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
