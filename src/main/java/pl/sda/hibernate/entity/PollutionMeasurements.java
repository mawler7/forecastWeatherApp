package pl.sda.hibernate.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class PollutionMeasurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private double carbonMonoxide;
    @Basic(optional = false)
    private double nitrogenMonoxide;
    @Basic(optional = false)
    private double nitrogenDioxide;
    @Basic(optional = false)
    private double ozone;
    @Basic(optional = false)
    private double sulphurDioxide;
    @Basic(optional = false)
    private double ammonia;
    @Basic(optional = false)
    private double fineParticulateMatter;
    @Basic(optional = false)
    private double coarseParticulateMatter;

    public PollutionMeasurements(double carbonMonoxide, double nitrogenMonoxide,
                                 double nitrogenDioxide, double ozone, double sulphurDioxide, double ammonia,
                                 double fineParticulateMatter, double coarseParticulateMatter) {

        this.carbonMonoxide = carbonMonoxide;
        this.nitrogenMonoxide = nitrogenMonoxide;
        this.nitrogenDioxide = nitrogenDioxide;
        this.ozone = ozone;
        this.sulphurDioxide = sulphurDioxide;
        this.ammonia = ammonia;
        this.fineParticulateMatter = fineParticulateMatter;
        this.coarseParticulateMatter = coarseParticulateMatter;
    }

    public PollutionMeasurements() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCarbonMonoxide() {
        return carbonMonoxide;
    }

    public void setCarbonMonoxide(double carbonMonoxide) {
        this.carbonMonoxide = carbonMonoxide;
    }

    public double getNitrogenMonoxide() {
        return nitrogenMonoxide;
    }

    public void setNitrogenMonoxide(double nitrogenMonoxide) {
        this.nitrogenMonoxide = nitrogenMonoxide;
    }

    public double getNitrogenDioxide() {
        return nitrogenDioxide;
    }

    public void setNitrogenDioxide(double nitrogenDioxide) {
        this.nitrogenDioxide = nitrogenDioxide;
    }

    public double getOzone() { return ozone; }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public double getSulphurDioxide() {
        return sulphurDioxide;
    }

    public void setSulphurDioxide(double sulfurDioxide) {
        this.sulphurDioxide = sulfurDioxide;
    }

    public double getAmmonia() {
        return ammonia;
    }

    public void setAmmonia(double ammonia) {
        this.ammonia = ammonia;
    }

    public double getFineParticulateMatter() {
        return fineParticulateMatter;
    }

    public void setFineParticulateMatter(double fineParticulateMatter) {
        this.fineParticulateMatter = fineParticulateMatter;
    }

    public double getCoarseParticulateMatter() {
        return coarseParticulateMatter;
    }

    public void setCoarseParticulateMatter(double coarseParticulateMatter) {
        this.coarseParticulateMatter = coarseParticulateMatter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PollutionMeasurements)) return false;
        PollutionMeasurements that = (PollutionMeasurements) o;
        return Double.compare(that.getCarbonMonoxide(), getCarbonMonoxide()) == 0 &&
                Double.compare(that.getNitrogenMonoxide(), getNitrogenMonoxide()) == 0 &&
                Double.compare(that.getNitrogenDioxide(), getNitrogenDioxide()) == 0 &&
                Double.compare(that.getOzone(), getOzone()) == 0 &&
                Double.compare(that.getSulphurDioxide(), getSulphurDioxide()) == 0 &&
                Double.compare(that.getAmmonia(), getAmmonia()) == 0 &&
                Double.compare(that.getFineParticulateMatter(), getFineParticulateMatter()) == 0 &&
                Double.compare(that.getCoarseParticulateMatter(), getCoarseParticulateMatter()) == 0;
    }

    @Override
    public String toString() {
        return "PollutionMeasurements{" +
                "id=" + id +
                ", carbonMonoxide=" + carbonMonoxide +
                ", nitrogenMonoxide=" + nitrogenMonoxide +
                ", nitrogenDioxide=" + nitrogenDioxide +
                ", ozone=" + ozone +
                ", sulphurDioxide=" + sulphurDioxide +
                ", ammonia=" + ammonia +
                ", fineParticlesMatter=" + fineParticulateMatter +
                ", coarseParticulateMatter=" + coarseParticulateMatter +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarbonMonoxide(), getNitrogenMonoxide(), getNitrogenDioxide(), getOzone(), getSulphurDioxide(), getAmmonia(), getFineParticulateMatter(), getCoarseParticulateMatter());
    }
}