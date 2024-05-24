import java.lang.reflect.Field;

public class JsonPathReader {

    public static Object readValue(Object obj, String jsonPath) throws IllegalAccessException {
        String[] pathSegments = jsonPath.split("\\.");
        Object currentObject = obj;

        for (String segment : pathSegments) {
            Field field = findField(currentObject.getClass(), segment);
            if (field == null) {
                throw new IllegalArgumentException("Field not found: " + segment);
            }
            field.setAccessible(true);
            currentObject = field.get(currentObject);
            if (currentObject == null) {
                return null;
            }
        }

        return currentObject;
    }

    private static Field findField(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}
