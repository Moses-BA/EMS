package com.ltp.employees.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skill_upgrade")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor

public class SkillUpgrade {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @NotBlank(message = "Skill column cannot be blank")
    @NonNull
    @Column(name = "skill", nullable = false)
    private String skill;

    @NotBlank(message = "Reason cannot be blank")
    @NonNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @Nullable
    @ManyToMany
    @JoinTable(
        name = "employee_skill",
        joinColumns = @JoinColumn(name = "skill_upgrade_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    private List<Employee> employee;

}