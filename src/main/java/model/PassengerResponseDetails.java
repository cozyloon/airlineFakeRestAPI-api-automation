package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PassengerResponseDetails {
    private String _id;
    private String name;
    private int trips;
    private List<Airline> airline;

    @Getter
    @Setter
    public static class Airline {
        private String _id;
        private int id;
        private String name;
        private String country;
        private String logo;
        private String slogan;
        private String head_quaters;
        private String website;
        private String established;
        private int __v;
    }

    private int __v;


}
