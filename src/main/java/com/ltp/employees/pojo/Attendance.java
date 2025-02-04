package com.ltp.employees.pojo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "attendance", uniqueConstraints = @UniqueConstraint(columnNames={"employee_id", "date"}))
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }

    @NotNull(message = "isPresent cannot be null")
    @Column(name = "is_present", nullable = false)
    @NonNull
    private boolean isPresent;

    public boolean getIsPresent() {
        return this.isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

}
