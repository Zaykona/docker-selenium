package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Addresses")
public class Addresses {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private int addressIid;

    @Column(name = "address")
    private String address;

    @Column(name = "reason")
    private String reason;

    @Column(name = "valid")
    private Boolean valid;

    public Addresses(int addressIid, String address, String reason, Boolean isValid) {
        this.addressIid = addressIid;
        this.address = address;
        this.reason = reason;
        this.valid = isValid;
    }

    public Addresses() {

    }
}
