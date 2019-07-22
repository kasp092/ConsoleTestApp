package file_DB;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import entities.TableBase;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DBacces<T extends TableBase> {

    public Class<T> getClazz() {
        try {
            Class clazz = Class.forName(TableBase.class.getPackageName() + "." +
                    new Scanner(System.in).nextLine());
            return clazz;
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class name!\n");
        }
        return null;
    }

    public Set<T> getList(T enity) {
        try {
            return new TreeSet<>(enity.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //      вывод списка классов наследников
    public void getExtended() {
        System.out.println("Entities list:");
        for (PojoClass pojoClass : PojoClassFactory.enumerateClassesByExtendingType(TableBase.class.getPackageName(),
                TableBase.class,
                null)) {

            System.out.println(" - " + pojoClass.getClazz().getSimpleName());
        }
    }
}
