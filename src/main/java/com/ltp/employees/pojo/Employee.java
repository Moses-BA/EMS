package com.ltp.employees.pojo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

public class Employee {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @NotBlank(message = "Name cannot be blank")
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @NonNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; 

    @NotBlank(message = "Email cannot be blank")
    @NonNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Address cannot be blank")
    @NonNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Job cannot be blank")
    @NonNull
    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "last_paid_date")
    private LocalDate lastPaidDate; 

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AnonymousOpinion> opinions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "employee_skill",
        joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "skill_upgrade_id", referencedColumnName = "id")
    )
    private List<SkillUpgrade> skills;

    @JsonIgnore
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private PerformanceTracking performanceTracking; 
} 
