package com.example.sopcode.view.pickerview;

import java.io.Serializable;
import java.util.List;

public class ChianProviceCity implements Serializable {

    /**
     * province : 北京市
     * cities : ["东城区","西城区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云区","延庆区"]
     */
    //@SerializedName("province")
    private String province;
    //@SerializedName("cities")
    private List<String> cities;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
