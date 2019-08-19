package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ValidAddresses")
public class ValidAddresses {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private int addressIid;

    @Column(name = "address")
    private String address;

    @Column(name = "reason")
    private String reason;

    public ValidAddresses(int addressIid, String address, String reason) {
        this.addressIid = addressIid;
        this.address = address;
        this.reason = reason;
    }

    public ValidAddresses() {

    }
}
