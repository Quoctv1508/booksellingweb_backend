package vn.alaxed.booksellingweb_backend.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 256)
    private String title;

    @Column(name = "icon")
    private String icon;

    @Column(name = "link")
    private String link;

    @Column(name = "data")
    @Lob
    private String data;

    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE
    } )
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
