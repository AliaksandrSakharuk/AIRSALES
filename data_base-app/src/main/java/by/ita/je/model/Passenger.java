package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private Long phoneNumber;
    private String passportNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
}
