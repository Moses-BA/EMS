package com.ltp.employees.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anonymous_opinion")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

public class AnonymousOpinion {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @NotBlank(message = "Talk cannot be blank")
    @NonNull
    @Column(name = "talk", nullable = false)
    private String talk;

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}