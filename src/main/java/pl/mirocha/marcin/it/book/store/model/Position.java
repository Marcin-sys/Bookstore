package pl.mirocha.marcin.it.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Position {
    private int id;
    private Book book;
    private int quantity;

    public void incrementQuantity(){
        this.quantity++;
    }
}
