package com.loja.suplementos.address;

import com.loja.suplementos.sale.dto.ShippingDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddress {

    @Id
    private String id;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String state;

    private String zipCode;

    public DeliveryAddress(String street, String number, String neighborhood, String city, String state, String zipCode) {
        this.id = UUID.randomUUID().toString();
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getFullAddress() {
        return street + ", " + number + ", " + neighborhood + ", " + city + ", " + state + ", " + zipCode;
    }
}
