
package in.demo.tapmedemo.Model;

import java.util.List;

public class Merchant {

    private String address;
    private String name;
    private String email;
    private String zipcode;
    private String merchantId;
    private List<ShopInfo> shopInfo = null;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public List<ShopInfo> getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(List<ShopInfo> shopInfo) {
        this.shopInfo = shopInfo;
    }

}
