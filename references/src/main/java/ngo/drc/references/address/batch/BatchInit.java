package ngo.drc.references.address.batch;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "batch_init_version")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchInit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long version;

    public BatchInit(Long version) {
        this.version = version;
    }
}
