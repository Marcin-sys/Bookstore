package pl.mirocha.marcin.it.book.store.model.dto.rest;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ResponseListDTO <T>{
    private final List<T> elements = new ArrayList<>();
}
