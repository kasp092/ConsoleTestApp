package entity;

import java.io.Serializable;
import java.util.Set;

public abstract class TableBase<T extends TableBase> implements Serializable, Comparable<TableBase> {
    TableBase() {
    }

    int id;

    public abstract Set<T> getList();

    public abstract int getId();

    public TableBase(int id) {
        this.id = id;
    }


    @Override
    public int compareTo(TableBase o) {
        return this.id > o.id ? 1 : -1;
    }

}
