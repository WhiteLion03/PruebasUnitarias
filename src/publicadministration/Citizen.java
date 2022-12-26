package publicadministration;

import data.Nif;

public class Citizen {
    private final Nif nif;
    private String name;
    private String address;
    private String mobileNumb;

    public Citizen (Nif nif, String name, String add, String mobile) {
        this.nif = nif;
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumb() {
        return mobileNumb;
    }

    public Nif getNif() { return nif; }



    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobileNumb(String mobileNumb) {
        this.mobileNumb = mobileNumb;
    }


    @Override
    public String toString() {
        return "Citizen{" +
                "nif=" + nif +
                '}';
    }
}
