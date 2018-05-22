package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ResearchMethod.
 */
@Entity
@Table(name = "research_method")
public class ResearchMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "jhi_file")
    private byte[] file;

    @Column(name = "jhi_file_content_type")
    private String fileContentType;

    @OneToMany(mappedBy = "researchMethod")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public ResearchMethod file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public ResearchMethod fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public ResearchMethod units(Set<Unit> units) {
        this.units = units;
        return this;
    }

    public ResearchMethod addUnit(Unit unit) {
        this.units.add(unit);
        unit.setResearchMethod(this);
        return this;
    }

    public ResearchMethod removeUnit(Unit unit) {
        this.units.remove(unit);
        unit.setResearchMethod(null);
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
        ResearchMethod researchMethod = (ResearchMethod) o;
        if (researchMethod.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), researchMethod.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResearchMethod{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            "}";
    }
}
