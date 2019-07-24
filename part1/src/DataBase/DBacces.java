package DataBase;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import entities.TableBase;
import entities.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

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

    //      вывод списка классов наследников
    public void getExtended() {
        System.out.println("Entities list:");
        for (PojoClass pojoClass : PojoClassFactory.enumerateClassesByExtendingType(TableBase.class.getPackageName(),
                TableBase.class,
                null)) {

            System.out.println(" - " + pojoClass.getClazz().getSimpleName());
        }
    }

    public void load(File file, Class<T> clazz) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
//        JavaType type = mapper.getTypeFactory().constructCollectionType(TreeSet.class, Class.forName(clazz.getName()));
        try {
            clazz.newInstance().setList(mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(TreeSet.class, Class.forName(clazz.getName()))));
        } catch (FileNotFoundException e) {
            System.out.println(file + " not founded");
        } catch (JsonParseException e) {
            System.out.println("Invalid data format.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
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