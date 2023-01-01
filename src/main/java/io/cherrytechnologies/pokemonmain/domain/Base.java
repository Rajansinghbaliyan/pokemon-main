package io.cherrytechnologies.pokemonmain.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
public class Base {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    @Convert(converter = org.hibernate.type.StandardBasicTypes.class)
    private UUID uuid;

    @Version
    private long version;

    @UpdateTimestamp
    private Timestamp lastModified;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
}
