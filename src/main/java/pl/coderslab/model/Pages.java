package pl.coderslab.model;

public class Pages {
   private int id;
   private String title;
   private String description;
   private String slug;



    @Override
    public String toString() {
        return "Pages{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }

    public Pages() {

    }

    public Pages(int id, String title, String description, String slug) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}



