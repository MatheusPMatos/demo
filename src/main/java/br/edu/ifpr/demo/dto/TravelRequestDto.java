package br.edu.ifpr.demo.dto;

public record TravelRequestDto(
    Long passengerId,
    String origin,
    String destination
) {}
