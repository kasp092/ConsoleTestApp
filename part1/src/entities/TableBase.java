package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

public abstract class TableBase<T extends TableBase> implements Serializable, Comparable<TableBase> {
    TableBase() {
    }

    int id;

    @JsonIgnore
    public abstract Set<T> getList();

    public abstract void setList(Set<T> entities);

    public int getId() {
        return id;
    }

    public TableBase(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return id + "  :  ";
    }

    @Override
    public int compareTo(TableBase o) {
        return this.id > o.id ? 1 : -1;
    }

}
