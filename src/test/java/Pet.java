public class Pet {
    private String id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tags[] tags;
    private String status;

    public Pet(String id, String name, String status) {
        this.id = id;
        this.category = new Category("0", "dogs");
        this.name = name;
        this.photoUrls = new String[]{"string"};
        this.tags = new Tags[]{new Tags("0", "string")};
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Object[] getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }


}
