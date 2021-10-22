package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long invertorNumber;
    private String namePlane;
    private String namePilot;
    private  int quantitySeats;
    private int seatsInLine;
    private int quantityLines;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private AirCompany company;
}
