import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Product product;

    public void add(Product porduct) {
        System.out.println("Add product: " + product);
        this.product = product;
    }

    public Product get() {
        System.out.println("Get product: " + product);
        return product;
    }

    public boolean isBusy(){
        return product != null;

    }

    public static void main(String[] args) throws InterruptedException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Vodka"));
        products.add(new Product("Beer"));
        products.add(new Product("Harring"));
        products.add(new Product("Pickle"));

        Channel channel = new Channel();
        Producer producer = new Producer(channel, products);
        Consumer consumer = new Consumer(channel);

        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Thread.sleep(5000);
    }
}

class Producer implements Runnable{
    private Channel channel;
    private List<Product> products;
    public Producer(Channel channel, List<Product> products) {
        this.channel = channel;
        this.products = products;
    }

    void putInChannel(Product product){
        if(!channel.isBusy()){
            channel.add(product);
        }
    }

    @Override
    public void run() {
        for(Product product : products){

            this.putInChannel(product);
        }
    }
}

class Consumer implements Runnable{
    private Channel channel;

    public Consumer(Channel channel) {
        this.channel = channel;
    }

    void buy(){
        if(channel.isBusy()){
            Product product = channel.get();


        }
    }

    @Override
    public void run() {
        buy();
    }
}

class Product{
    private String name;

    public Product(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}