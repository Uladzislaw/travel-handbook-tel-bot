package com.uladzislau.travel_handbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "content")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contentIdSequence")
    @SequenceGenerator(name = "contentIdSequence", sequenceName = "content_id_seq",
            initialValue = 50, allocationSize = 10)
    @Column(columnDefinition = "serial")
    private Long id;

    private String text;

    @ManyToOne
    private City city;
}
