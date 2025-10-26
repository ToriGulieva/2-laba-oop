import java.util.Date;
import java.text.SimpleDateFormat;

public class Motorcycle implements Transport {
    private class Model {
        String modelName = null;
        int price = -1;
        Model prev = null;
        Model next = null;
        public Model() {}
        public Model(String modelName, int price) {
            this.modelName = modelName;
            this.price = price;
        }
    }

    private String brand;
    private int size = 0;
    private Model head;
    private long lastModified;

    {
        lastModified = System.currentTimeMillis();
    }

    public Motorcycle(String brand, int numberOfModels) {
        this.brand = brand;
        //создаем голову
        head = new Model();
        head.next = head;
        head.prev = head;

        for (int i = 1; i <= numberOfModels; i++) {
            try {
                addModel("Model_" + i, 50000 + i * 10000);
            } catch (DuplicateModelNameException e) {}
        }
        updateLastModified();
    }


    // Возвращает ссылку на объект по имени
    private Model modelExists(String modelName) {
        Model current = head.next;
        while (current != head) {
            if (current.modelName != null && current.modelName.equals(modelName)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void updateLastModified() {
        lastModified = System.currentTimeMillis();
    }
    // Метод для получения даты последней модификации как отформатированной строки
    public String getLastModified() {
        Date date = new Date(lastModified);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


    @Override
    public String getBrand() {
        return brand;
    }
    @Override
    public void setBrand(String brand) {
        this.brand = brand;
        updateLastModified();
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void addModel(String modelName, int price) throws DuplicateModelNameException {
        if (price <= 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        if (modelName == null) {
            throw new IllegalArgumentException("Имя модели не может быть пустым");
        }
        if (modelExists(modelName) != null) {
            throw new DuplicateModelNameException(modelName);
        }

        Model newModel = new Model(modelName, price);
        newModel.next = head;
        newModel.prev = head.prev;
        head.prev.next = newModel;
        head.prev = newModel;
        size++;
        updateLastModified();
    }
    @Override
    public void modifyModelName(String oldName, String newName)
            throws IllegalArgumentException, DuplicateModelNameException, NoSuchModelNameException {
        if (newName == null) {//1
            throw new IllegalArgumentException("Новое имя модели не может быть пустым");
        }
        Model current = head.next;
        Model ref=null;
        while (current != head) {
            if (current.modelName.equals(newName)) throw new DuplicateModelNameException(newName);
            if (current.modelName.equals(oldName)) ref=current;
            current= current.next;
        }
        if (ref == null) throw new NoSuchModelNameException(oldName);
        ref.modelName = newName;
        updateLastModified();
    }
    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        Model current = head.next;
        if(current.modelName != null && modelExists(current.modelName) != null) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            updateLastModified();
        }
        else throw new NoSuchModelNameException(modelName);
    }
    @Override
    public int getPriceFromModel(String modelName) throws NoSuchModelNameException {
        Model model = modelExists(modelName);
        if (model != null) {
            return model.price;
        }
        throw new NoSuchModelNameException(modelName);
    }
    @Override
    public void setPriceForModel(String modelName, int price) throws NoSuchModelNameException {
        if (price <= 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Model model = modelExists(modelName);
        if (model != null) {

            model.price = price;
            updateLastModified();
            return;
        }
        throw new NoSuchModelNameException(modelName);
    }
    @Override
    public int[] getAllPrices() {
        int[] prices = new int[size];
        Model current = head.next;
        int index = 0;
        while (current != head && index < size) {
            prices[index] = current.price;
            current = current.next;
            index++;
        }
        return prices;
    }
    @Override
    public String[] getAllModelNames() {
        String[] names = new String[size];
        Model current = head.next;
        int index = 0;
        while (current != head && index < size) {
            names[index] = current.modelName;
            current = current.next;
            index++;
        }
        return names;
    }
}