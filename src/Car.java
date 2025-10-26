import java.util.Arrays;

public class Car implements Transport {
    private String marka;

    private class Model {
        private String modelName;
        private int price = -1;
        public Model(String modelName, int price) {
            this.modelName = modelName;
            this.price = price;
        }
        public String getModelName() { return modelName; }
        public int getPrice() { return price; }
        public void setModelName(String modelName) { this.modelName = modelName; }
        public void setPrice(int price) { this.price = price; }
    }

    private Model[] models;

    public Car(String marka, int size) {
        this.marka = marka;
        this.models = new Model[size];
        for (int i = 0; i < size; i++) {
            this.models[i] = new Model("Model_" + (i + 1), 10000 + i * 5000);
        }
    }

    private Model modelExists(String modelName) {
        for (Model model : models) {
            if (model != null && model.getModelName() != null && model.getModelName().equals(modelName)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public String getBrand() {
        return marka;
    }
    @Override
    public void setBrand(String brand) {
        this.marka = brand;
    }
    @Override
    public int getSize() {
        return models.length;
    }
    @Override
    public void addModel(String modelName, int price) throws DuplicateModelNameException {
        if (modelName == null) {
            throw new IllegalArgumentException("Имя модели не может быть пустым");
        }
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        if (modelExists(modelName) != null) {
            throw new DuplicateModelNameException(modelName);
        }

        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);

    }
    @Override
    public void modifyModelName(String oldName, String newName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        if (newName == null) {
            throw new IllegalArgumentException("Новое имя модели не может быть пустым");
        }
        int index=-1;
         for (int i = 0; i < models.length; i++) {
            if (models[i].modelName.equals(newName)) throw new DuplicateModelNameException(newName);
            if (models[i].modelName.equals(oldName))index=i;
        }
        if (index==-1)throw new NoSuchModelNameException(oldName);
        models[index].setModelName(newName);

    }
    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        int index = -1;
        for (int i = 0; i < models.length; i++) {
            if (models[i] != null && models[i].getModelName() != null && models[i].getModelName().equals(modelName)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchModelNameException(modelName);
        }
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length -1);
    }
    @Override
    public int getPriceFromModel(String name) throws NoSuchModelNameException {
        Model model = modelExists(name);
        if (model != null) {
            return model.getPrice();
        }
        throw new NoSuchModelNameException(name);
    }
    @Override
    public void setPriceForModel(String name, int price) throws NoSuchModelNameException {
        if (price <= 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Model model = modelExists(name);
        if (model != null) {

            model.setPrice(price);
            return;
        }
        throw new NoSuchModelNameException(name);
    }
    @Override
    public int[] getAllPrices() {
        int[] prices = new int[models.length];
        for (int i = 0; i < models.length; i++) {
            if (models[i] != null) {
                prices[i] = models[i].getPrice();
            } else {
                prices[i] = -1;
            }
        }
        return prices;
    }
    @Override
    public String[] getAllModelNames() {
        String[] names = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].getModelName();
        }
        return names;
    }
}