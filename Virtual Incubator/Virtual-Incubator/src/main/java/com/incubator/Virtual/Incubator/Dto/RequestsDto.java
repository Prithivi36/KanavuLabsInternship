package com.incubator.Virtual.Incubator.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestsDto<T> {
    T person;
    Long rqstId;
    String message;
    LocalDateTime dateTime;
}
