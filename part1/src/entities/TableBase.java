package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public abstract class TableBase implements  Comparable<TableBase> {
    TableBase() {
    }

    protected int id;

    @JsonIgnore
    public abstract Set<? extends TableBase> getList();

    public abstract void setList(Set<? extends TableBase> entities);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableBase tableBase = (TableBase) o;
        return id == tableBase.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
