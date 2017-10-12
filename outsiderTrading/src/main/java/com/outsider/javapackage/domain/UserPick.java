package com.outsider.javapackage.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A UserPick.
 */
@Entity
@Table(name = "user_pick")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserPick implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "symbl", nullable = false)
    private String symbl;

    @NotNull
    @Column(name = "jhi_own", nullable = false)
    private Boolean own;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "entry_price", nullable = false)
    private Double entryPrice;

    @NotNull
    @Column(name = "up", nullable = false)
    private Boolean up;

    @NotNull
    @Column(name = "target", nullable = false)
    private Double target;

    @NotNull
    @Column(name = "jhi_time", nullable = false)
    private String time;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbl() {
        return symbl;
    }

    public UserPick symbl(String symbl) {
        this.symbl = symbl;
        return this;
    }

    public void setSymbl(String symbl) {
        this.symbl = symbl;
    }

    public Boolean isOwn() {
        return own;
    }

    public UserPick own(Boolean own) {
        this.own = own;
        return this;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public Double getEntryPrice() {
        return entryPrice;
    }

    public UserPick entryPrice(Double entryPrice) {
        this.entryPrice = entryPrice;
        return this;
    }

    public void setEntryPrice(Double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Boolean isUp() {
        return up;
    }

    public UserPick up(Boolean up) {
        this.up = up;
        return this;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Double getTarget() {
        return target;
    }

    public UserPick target(Double target) {
        this.target = target;
        return this;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public String getTime() {
        return time;
    }

    public UserPick time(String time) {
        this.time = time;
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public UserPick user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        UserPick userPick = (UserPick) o;
        if (userPick.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userPick.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserPick{" +
            "id=" + getId() +
            ", symbl='" + getSymbl() + "'" +
            ", own='" + isOwn() + "'" +
            ", entryPrice='" + getEntryPrice() + "'" +
            ", up='" + isUp() + "'" +
            ", target='" + getTarget() + "'" +
            ", time='" + getTime() + "'" +
            "}";
    }
}
