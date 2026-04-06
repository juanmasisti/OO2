package ejercicio5;

import java.time.LocalDate;

public class HotelStay extends Product {
    private double quote;
    private Hotel hotel;

    public HotelStay(double quote, TimePeriod timePeriod, Hotel hotel) {
        this.quote = quote;
        this.timePeriod = timePeriod;
        this.hotel = hotel;
    }

    public double priceFactor() {
        return this.quote / this.price();
    }

    public double price() {
        return this.hotel.calculatePrice(this.timePeriod.duration());
    }
    
    public double getQuote() {
        return this.quote;
    }
}
