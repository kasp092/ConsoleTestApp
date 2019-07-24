package DataBase;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import entities.TableBase;

import java.util.*;

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
            return new TreeSet(enity.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //      возвращает список классов наследников
    public Set<String> getExtended() {
        Set<String> set = new TreeSet<>();
        System.out.println("Entities list:");
        for (PojoClass pojoClass : PojoClassFactory.enumerateClassesByExtendingType(TableBase.class.getPackageName(),
                TableBase.class,
                null)) {
            set.add(pojoClass.getClazz().getSimpleName());
        }
        return set;
    }

//    public void load(File file, Class<T> clazz) throws Exception {
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            clazz.newInstance().setList(mapper.readValue(file,
//                    mapper.getTypeFactory().constructCollectionType(TreeSet.class, Class.forName(clazz.getName()))));
//        } catch (FileNotFoundException e) {
//            System.out.println(file + " not founded");
//        } catch (JsonParseException e) {
//            System.out.println("Invalid data format.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

    /*
    * Constructor<?> constructor = clazz.getConstructor(int.class);

        Mapper

        //Mapper get id
        TableBase tableBase = constructor.newInstance(id);

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        fields = fields.stream().filter(z -> !z.getName().equals("id")).collect(Collectors.toList());
        for (Field field : fields) {
            field.set(tableBase, // maper.getValue(field.getName));
    * */