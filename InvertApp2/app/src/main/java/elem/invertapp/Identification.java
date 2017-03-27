package elem.invertapp;

/**
 * Created by elliot on 4/3/2016.
 */
public class Identification {

    private String idName;
    private int imageFull;
    private int imageTn;
    private int[] attributeResponses;

    public Identification(String idName, int imageFull, int imageTn, int[] attributeResponses){
        this.idName = idName;
        this.imageFull = imageFull;
        this.imageTn = imageTn;
        this.attributeResponses = attributeResponses;
    }

    public String getIdName() {
        return idName;
    }

    public int getImageFull() {
        return imageFull;
    }

    public int getImageTn() {
        return imageTn;
    }

    public int[] getAttributeResponses() {
        return attributeResponses;
    }
}
