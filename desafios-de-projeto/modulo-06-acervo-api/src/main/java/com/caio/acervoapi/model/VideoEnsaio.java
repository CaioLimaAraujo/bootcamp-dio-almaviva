package com.caio.acervoapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("VIDEO_ENSAIO")
public class VideoEnsaio extends Obra {

    private String url;
    private int duracaoMinutos;
}