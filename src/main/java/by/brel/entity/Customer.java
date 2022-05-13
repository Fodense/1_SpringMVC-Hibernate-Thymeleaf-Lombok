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
@Table(name = "customers")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@ApiModel(description = "Customer entity.")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    @ApiModelProperty(value = "${customers.entity.idCustomer}", example = "1", required = true, position = 0)
    private long idCustomer;

    @Column(name = "first_name")
    @ApiModelProperty(value = "${customers.entity.firstName}", example = "Ivan", position = 1)
    private String firstName;

    @Column(name = "last_name")
    @ApiModelProperty(value = "${customers.entity.lastName}", example = "Ivanovich", position = 2)
    private String lastName;

    @Column(name = "date_birth")
    @ApiModelProperty(value = "${customers.entity.dateBirth}", example = "1970.01.01", position = 3)
    private String dateBirth;

    @Column(name = "mobile_phone")
    @ApiModelProperty(value = "${customers.entity.mobilePhone}", example = "+375291111111", position = 4)
    private String mobilePhone;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Balance> balances;

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
