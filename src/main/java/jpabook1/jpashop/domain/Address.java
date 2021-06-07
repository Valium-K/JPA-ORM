package jpabook1.jpashop.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

// 값 타입
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public String getCity() {
        return city;
    }
    private void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    private void setStreet(String street) {
        this.street = street;
    }
    public String getZipcode() {
        return zipcode;
    }
    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        // 프록시 객체일 경우를 고려해 getter를 사용 && 권장되는 방법
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
