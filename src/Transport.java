public interface Transport {
    String getBrand();
    void setBrand(String brand);
    int getSize();
    void addModel(String modelName, int price) throws DuplicateModelNameException;
    void modifyModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    void removeModel(String modelName) throws NoSuchModelNameException;
    int getPriceFromModel(String modelName) throws NoSuchModelNameException;
    void setPriceForModel(String modelName, int price) throws NoSuchModelNameException;
    int[] getAllPrices();
    String[] getAllModelNames();

    default String getLastModified() {
        return "Not implemented";
    }
}