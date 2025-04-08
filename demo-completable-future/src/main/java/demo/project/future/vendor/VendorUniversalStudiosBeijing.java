package demo.project.future.vendor;

public class VendorUniversalStudiosBeijing implements Vendor {

    @Override
    public RequestVendorResult request(RequestVendorParam param) {
        RequestVendorResult result = new RequestVendorResult();
        try {
            Thread.sleep(70 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
