import java.text.DecimalFormat;

public class PackageOrder {
    private int package_id, quantity;
    private String customer_id, destination, date_of_purchase, delivery_date, shipping_date, item_id, url;
    private double total_price, sub_total;
    public PackageOrder(int package_id, String customer_id, double total_price, String item_id, int quantity, double sub_total,
                        String destination,String date_of_purchase, String delivery_date, String shipping_date,
                        String url){
        this.package_id=package_id;
        this.quantity=quantity;
        this.customer_id=customer_id;
        this.destination=destination;
        this.date_of_purchase=date_of_purchase;
        this.delivery_date=delivery_date;
        this.shipping_date=shipping_date;
        this.item_id=item_id;
        this.sub_total=sub_total;
        this.total_price=total_price;
        this.url=url;


    }
    // returns array in table format
    public String[] getPackage(){
        DecimalFormat df = new DecimalFormat("0.00");

        return new String[]{"" + package_id,customer_id,"$"+df.format(total_price), item_id, "" + quantity,
                "$"+df.format(sub_total),destination,date_of_purchase.substring(0,10),delivery_date.substring(0,10)
                , shipping_date.substring(0,10), url};
    }
    public String getDOP(){
        return date_of_purchase;
    }
    public double getTotal(){
        return total_price;
    }
    public double getItemPrice(){
        return sub_total;
    }
    public String getName(){
        return customer_id;
    }

    // returns all characters for keyword sorting purposes
    public String toString(){
        return ("" + package_id+" "+customer_id+" "+destination+" "+date_of_purchase+" "+delivery_date
                +" "+"$"+total_price+" "+ shipping_date+" "+ item_id+" " + quantity+" " + "$"+sub_total + url);
    }
    public boolean findKeyword(String keyword){
        if(keyword.equals("")){
            return false;
        }
        String package_contents = toString();
        int len = keyword.length();
        int point=0;
        while(true){
            if(package_contents.length()==keyword.length()+point+1){
                return false;
            }
            else if(package_contents.substring(point,point+len).equalsIgnoreCase(keyword)){
                return true;
            }
            else{
                point++;
            }
        }
    }
    public boolean isTotalCheaper(PackageOrder other_package){
        if(this.getTotal()<other_package.getTotal()){
            return true;
        }
        return false;
    }
    public boolean isItemCheaper(PackageOrder other_package){
        if(this.getItemPrice()<other_package.getItemPrice()){
            return true;
        }
        return false;
    }


    public boolean isOlder(PackageOrder other_package){
        int my_day=Integer.parseInt(this.getDOP().substring(8,10));
        int my_month=Integer.parseInt(this.getDOP().substring(5,7));
        int my_year=Integer.parseInt(this.getDOP().substring(0,4));
        int your_day=Integer.parseInt(other_package.getDOP().substring(8,10));
        int your_month=Integer.parseInt(other_package.getDOP().substring(5,7));
        int your_year=Integer.parseInt(other_package.getDOP().substring(0,4));
        if(your_year<my_year) {
            return true;
        }
        else if(your_year==my_year){
            if(your_month<my_month){
                return true;
            }
            else if(your_month==my_month){
                if(your_day<my_day){
                    return true;
                }
                else if(your_day==my_day){
                    return false;
                }
            }
        }

        return false;
    }
}
