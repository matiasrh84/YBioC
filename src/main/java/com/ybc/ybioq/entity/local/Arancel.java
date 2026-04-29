package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aranceles", schema = "bioquimicos")
public class Arancel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}