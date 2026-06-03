package com.ybc.ybioq.entity.local;

public enum TipoInforme {

    /** Análisis estándar presentados en filas (la gran mayoría). */
    FILAS,

    /** Análisis con layout de dos columnas (ej: hemograma diferencial). */
    COLUMNAS,

    /** Electroforesis de proteínas (proteinograma sérico). */
    PROTEINOGRAMA,

    /** Cultivo bacteriológico con antibiograma. */
    CULTIVO,

    /** Hemograma con parámetros de posición fija. */
    HEMOGRAMA_FIJO,

    /** Hemograma presentado en filas como análisis estándar. */
    HEMOGRAMA_FILAS
}
