package med.supi.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String complement;
    private String number;
    private String uf;

    public Address(AddressRecord address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.zipcode = address.zipcode();
        this.uf = address.uf();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void updateAddress(AddressRecord address) {
        if (address.street() != null)
            this.street = address.street();
        
        if (address.neighborhood() != null)
            this.neighborhood = address.neighborhood();

        if (address.zipcode() != null)
            this.zipcode = address.zipcode();

        if (address.uf() != null)
            this.uf = address.uf();

        if (address.city() != null)
            this.city = address.city();
        
        if (address.number() != null)
            this.number = address.number();

        if (address.complement() != null)
            this.complement = address.complement();
    }
}
