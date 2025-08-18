package com.guibarao.seguranca_privada.models;
import java.time.LocalDate;

public record Indisponibilidade(
       LocalDate dataInicial,
       LocalDate dataFinal

) {}