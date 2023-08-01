package com.daniel.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class AbstractEntity implements Serializable {
    @Id
    private Integer id;

    @CreatedDate
    @Column(name="creationDate", nullable = false)
    private Date creationDate;

    @LastModifiedDate
    @Column(name="lastModifiedDate")
    @JsonIgnore
    private Instant lastUpdateDate;
}
