package com.guibarao.seguranca_privada.models;
import java.time.LocalDate;

public record Turno(
        LocalDate horarioInicial,
        LocalDate horarioFinal
) {}