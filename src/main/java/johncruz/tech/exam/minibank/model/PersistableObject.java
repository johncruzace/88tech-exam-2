package johncruz.tech.exam.minibank.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class PersistableObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String createdBy;

    @Column
    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }
}
