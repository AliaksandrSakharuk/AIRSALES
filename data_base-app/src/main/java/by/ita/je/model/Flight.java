package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberFlight;
    private String departureCity;
    private LocalDateTime departureDateTime;
    private int durationFlight;
    private String arriveCity;
    private LocalDateTime arriveDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;
}
