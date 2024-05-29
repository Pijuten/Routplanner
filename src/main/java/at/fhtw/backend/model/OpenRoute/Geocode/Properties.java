package at.fhtw.backend.model.OpenRoute.Geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties{
    public String name;
    public String match_type;
    public String accuracy;
    public String country;
    public String country_gid;
    public String country_a;
    public String region_a;
    public String county_a;
    public String street;
    public String housenumber;
    public String postalcode;
}