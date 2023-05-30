package example.entries;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyList {
    @XmlElement(name = "item")
    private List<Currency> items = null;

    public List<Currency> getItems() {
        return items;
    }

    public void setItems(List<Currency> items) {
        this.items = items;
    }
}
