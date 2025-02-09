package com.sunbeam.booking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(exclude = "id") // ✅ Excludes 'id' from toString() to prevent long logs
@EqualsAndHashCode(callSuper = false) // ✅ Fixes Lombok Warning
public abstract class BaseEntity { // ✅ Changed to 'abstract' to avoid direct instantiation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false, nullable = false) // ✅ Ensures non-null timestamps
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false) // ✅ Ensures non-null timestamps
    private LocalDateTime updatedOn;
}
