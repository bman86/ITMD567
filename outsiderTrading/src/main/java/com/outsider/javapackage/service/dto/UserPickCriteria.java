package com.outsider.javapackage.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the UserPick entity. This class is used in UserPickResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /user-picks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UserPickCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter symbl;

    private BooleanFilter own;

    private DoubleFilter entryPrice;

    private BooleanFilter up;

    private DoubleFilter target;

    private StringFilter time;

    private LongFilter userId;

    public UserPickCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSymbl() {
        return symbl;
    }

    public void setSymbl(StringFilter symbl) {
        this.symbl = symbl;
    }

    public BooleanFilter getOwn() {
        return own;
    }

    public void setOwn(BooleanFilter own) {
        this.own = own;
    }

    public DoubleFilter getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(DoubleFilter entryPrice) {
        this.entryPrice = entryPrice;
    }

    public BooleanFilter getUp() {
        return up;
    }

    public void setUp(BooleanFilter up) {
        this.up = up;
    }

    public DoubleFilter getTarget() {
        return target;
    }

    public void setTarget(DoubleFilter target) {
        this.target = target;
    }

    public StringFilter getTime() {
        return time;
    }

    public void setTime(StringFilter time) {
        this.time = time;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserPickCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (symbl != null ? "symbl=" + symbl + ", " : "") +
                (own != null ? "own=" + own + ", " : "") +
                (entryPrice != null ? "entryPrice=" + entryPrice + ", " : "") +
                (up != null ? "up=" + up + ", " : "") +
                (target != null ? "target=" + target + ", " : "") +
                (time != null ? "time=" + time + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

}
