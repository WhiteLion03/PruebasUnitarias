package publicadministration;

import data.Nif;

public class Citizen {
    private Nif nif;
    private String name;
    private String adress;
    private String mobileNumb;

    public Citizen (String name, String add, String mobile) {
        this.name = name;
        this.adress = add;
        this.mobileNumb = mobile;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getMobileNumb() {
        return mobileNumb;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "nif=" + nif +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", mobileNumb='" + mobileNumb + '\'' +
                '}';
    }
}
