package com.muhamadagus.booknetwork.feedback;

import com.muhamadagus.booknetwork.book.Book;
import com.muhamadagus.booknetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {

    private double rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "bookId",nullable = false)
    private Book book;
}
