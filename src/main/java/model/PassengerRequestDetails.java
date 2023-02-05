package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerRequestDetails {
    private String name;
    private int trips;
    private int airline;
}
