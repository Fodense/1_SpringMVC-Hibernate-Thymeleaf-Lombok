package by.brel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Tariff entity.")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tariff")
    @ApiModelProperty(value = "${tariffs.entity.idTariff}", example = "1", required = true, position = 0)
    private long idTariff;

    @Column(name = "title")
    @ApiModelProperty(value = "${tariffs.entity.title}", example = "Business", position = 1)
    private String title;

    @Column(name = "price")
    @ApiModelProperty(value = "${tariffs.entity.price}", example = "1000", position = 2)
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
