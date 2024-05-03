package com.muhamadagus.booknetwork.history;

import com.muhamadagus.booknetwork.book.Book;
import com.muhamadagus.booknetwork.common.BaseEntity;
import com.muhamadagus.booknetwork.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class BookTransactionHistory extends BaseEntity {

    //user relationship
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    //book relationship
    @ManyToOne
    @JoinColumn(name = "bookId",nullable = false)
    private Book book;

    private boolean returned;
    private boolean returnApproved;
}
