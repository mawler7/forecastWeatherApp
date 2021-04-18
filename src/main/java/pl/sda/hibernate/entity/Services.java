package pl.sda.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Services {

    @Id
    private Long id;

    private String serviceName;

    private String url;

    public Services(String serviceName, String url) {
        this.serviceName = serviceName;
        this.url = url;
    }

    public Services(){}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return Objects.equals(id, services.id) &&
                Objects.equals(serviceName, services.serviceName) &&
                Objects.equals(url, services.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, url);
    }

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
