package project.eyack.jolup.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DrugInventory {

    private String drugId;
    private String drugName;
    private String manName;
    private int cnt;
    private String hpid;
    private String drugBarcode;

    @Override
    public String toString() {
        return "DrugInventory{" +
                "drugId='" + drugId + '\'' +
                ", drugName='" + drugName + '\'' +
                ", manName='" + manName + '\'' +
                ", cnt=" + cnt +
                ", hpid='" + hpid + '\'' +
                ", drugBarcode='" + drugBarcode + '\'' +
                '}';
    }
}
