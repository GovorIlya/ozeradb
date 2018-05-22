package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RatingMethod.
 */
@Entity
@Table(name = "rating_method")
public class RatingMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "rating_document")
    private byte[] ratingDocument;

    @Column(name = "rating_document_content_type")
    private String ratingDocumentContentType;

    @OneToMany(mappedBy = "ratingMethod")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getRatingDocument() {
        return ratingDocument;
    }

    public RatingMethod ratingDocument(byte[] ratingDocument) {
        this.ratingDocument = ratingDocument;
        return this;
    }

    public void setRatingDocument(byte[] ratingDocument) {
        this.ratingDocument = ratingDocument;
    }

    public String getRatingDocumentContentType() {
        return ratingDocumentContentType;
    }

    public RatingMethod ratingDocumentContentType(String ratingDocumentContentType) {
        this.ratingDocumentContentType = ratingDocumentContentType;
        return this;
    }

    public void setRatingDocumentContentType(String ratingDocumentContentType) {
        this.ratingDocumentContentType = ratingDocumentContentType;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public RatingMethod units(Set<Unit> units) {
        this.units = units;
        return this;
    }

    public RatingMethod addUnit(Unit unit) {
        this.units.add(unit);
        unit.setRatingMethod(this);
        return this;
    }

    public RatingMethod removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.setRatingMethod(null);
        return this;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RatingMethod ratingMethod = (RatingMethod) o;
        if (ratingMethod.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ratingMethod.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RatingMethod{" +
            "id=" + getId() +
            ", ratingDocument='" + getRatingDocument() + "'" +
            ", ratingDocumentContentType='" + getRatingDocumentContentType() + "'" +
            "}";
    }
}
