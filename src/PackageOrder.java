public class PackageOrder {
    private int package_id, quantity;
    private String customer_id, destination, date_of_purchase, delivery_date, shipping_status, item_id;
    private double total_price, sub_total;
    public PackageOrder(int package_id, int quantity, String customer_id, String destination, String date_of_purchase,
                        String delivery_date, String shipping_status, String item_id, double sub_total){
        this.package_id=package_id;
        this.quantity=quantity;
        this.customer_id=customer_id;
        this.destination=destination;
        this.date_of_purchase=date_of_purchase;
        this.delivery_date=delivery_date;
        this.shipping_status=shipping_status;
        this.item_id=item_id;
        this.sub_total=sub_total;
        this.total_price=sub_total;


    }
    public String[] getPackage(){
        return new String[]{"" + package_id,customer_id,destination,date_of_purchase,delivery_date
        ,"$"+total_price, shipping_status, item_id, "" + quantity, "$"+sub_total};
    }
    public String getDOP(){
        return date_of_purchase;
    }
    public String toString(){
        return ("" + package_id+" "+customer_id+" "+destination+" "+date_of_purchase+" "+delivery_date
                +" "+"$"+total_price+" "+ shipping_status+" "+ item_id+" " + quantity+" " + "$"+sub_total);
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

    public boolean isOlder(PackageOrder other_package){
        int my_day=Integer.parseInt(this.getDOP().substring(3,5));
        int my_month=Integer.parseInt(this.getDOP().substring(0,2));
        int my_year=Integer.parseInt(this.getDOP().substring(6,8));
        int your_day=Integer.parseInt(other_package.getDOP().substring(3,5));
        int your_month=Integer.parseInt(other_package.getDOP().substring(0,2));
        int your_year=Integer.parseInt(other_package.getDOP().substring(6,8));
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
