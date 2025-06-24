package com.loja.suplementos.customer.domain;

import com.loja.suplementos.address.DeliveryAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String cpf;

    private Date birthDate;

    private Set<DeliveryAddress> deliveryAddresses;

    public String getFullName() {
        return name + " " + lastName;
    }
}
