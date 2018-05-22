package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Unit.
 */
@Entity
@Table(name = "unit")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @OneToOne
    @JoinColumn(unique = true)
    private UnitDescription description;

    @OneToMany(mappedBy = "unit")
    @JsonIgnore
    private Set<Image> images = new HashSet<>();

    @ManyToOne
    private ResearchMethod researchMethod;

    @ManyToOne
    private RatingMethod ratingMethod;

    @ManyToOne
    private TypesProblems typesProblems;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public Unit unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public UnitDescription getDescription() {
        return description;
    }

    public Unit description(UnitDescription unitDescription) {
        this.description = unitDescription;
        return this;
    }

    public void setDescription(UnitDescription unitDescription) {
        this.description = unitDescription;
    }

    public Set<Image> getImages() {
        return images;
    }

    public Unit images(Set<Image> images) {
        this.images = images;
        return this;
    }

    public Unit addImage(Image image) {
        this.images.add(image);
        image.setUnit(this);
        return this;
    }

    public Unit removeImage(Image image) {
        this.images.remove(image);
        image.setUnit(null);
        return this;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public ResearchMethod getResearchMethod() {
        return researchMethod;
    }

    public Unit researchMethod(ResearchMethod researchMethod) {
        this.researchMethod = researchMethod;
        return this;
    }

    public void setResearchMethod(ResearchMethod researchMethod) {
        this.researchMethod = researchMethod;
    }

    public RatingMethod getRatingMethod() {
        return ratingMethod;
    }

    public Unit ratingMethod(RatingMethod ratingMethod) {
        this.ratingMethod = ratingMethod;
        return this;
    }

    public void setRatingMethod(RatingMethod ratingMethod) {
        this.ratingMethod = ratingMethod;
    }

    public TypesProblems getTypesProblems() {
        return typesProblems;
    }

    public Unit typesProblems(TypesProblems typesProblems) {
        this.typesProblems = typesProblems;
        return this;
    }

    public void setTypesProblems(TypesProblems typesProblems) {
        this.typesProblems = typesProblems;
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
        Unit unit = (Unit) o;
        if (unit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Unit{" +
            "id=" + getId() +
            ", unitName='" + getUnitName() + "'" +
            "}";
    }
}
