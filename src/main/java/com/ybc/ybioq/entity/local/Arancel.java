package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aranceles", schema = "bioquimicos")
public class Arancel {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
}