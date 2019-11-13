package com.zemoga.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "portfolio")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name="hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "idportfolio")
    private Integer portfolioId;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "twitterUserName", updatable = false)
    private String twitterUserName;
}
