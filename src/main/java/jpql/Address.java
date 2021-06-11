package jpql;

import javax.persistence.Embeddable;

@Embeddable
public abstract class Address {
    private String city;
    private String street;
    private String zipcode;
}
