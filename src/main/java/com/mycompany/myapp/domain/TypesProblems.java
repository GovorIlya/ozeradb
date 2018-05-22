package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TypesProblems.
 */
@Entity
@Table(name = "types_problems")
public class TypesProblems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "problem_document")
    private byte[] problemDocument;

    @Column(name = "problem_document_content_type")
    private String problemDocumentContentType;

    @OneToMany(mappedBy = "typesProblems")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getProblemDocument() {
        return problemDocument;
    }

    public TypesProblems problemDocument(byte[] problemDocument) {
        this.problemDocument = problemDocument;
        return this;
    }

    public void setProblemDocument(byte[] problemDocument) {
        this.problemDocument = problemDocument;
    }

    public String getProblemDocumentContentType() {
        return problemDocumentContentType;
    }

    public TypesProblems problemDocumentContentType(String problemDocumentContentType) {
        this.problemDocumentContentType = problemDocumentContentType;
        return this;
    }

    public void setProblemDocumentContentType(String problemDocumentContentType) {
        this.problemDocumentContentType = problemDocumentContentType;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public TypesProblems units(Set<Unit> units) {
        this.units = units;
        return this;
    }

    public TypesProblems addUnit(Unit unit) {
        this.units.add(unit);
        unit.setTypesProblems(this);
        return this;
    }

    public TypesProblems removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.setTypesProblems(null);
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
        TypesProblems typesProblems = (TypesProblems) o;
        if (typesProblems.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typesProblems.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypesProblems{" +
            "id=" + getId() +
            ", problemDocument='" + getProblemDocument() + "'" +
            ", problemDocumentContentType='" + getProblemDocumentContentType() + "'" +
            "}";
    }
}
